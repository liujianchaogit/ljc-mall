package com.ljc.order.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljc.common.api.R;
import com.ljc.common.dto.ware.FareVo;
import com.ljc.common.dto.ware.MemberAddressVo;
import com.ljc.common.dto.ware.SkuStock;
import com.ljc.common.exceptions.NoStockException;
import com.ljc.order.entity.Order;
import com.ljc.order.entity.OrderItem;
import com.ljc.order.feign.CartFeign;
import com.ljc.order.feign.MemberFeign;
import com.ljc.order.feign.ProductFeign;
import com.ljc.order.feign.WareFeign;
import com.ljc.order.mapper.OrderMapper;
import com.ljc.order.service.IOrderItemService;
import com.ljc.order.service.IOrderService;
import com.ljc.order.to.OrderCreateTo;
import com.ljc.order.to.SpuInfoVo;
import com.ljc.order.vo.*;
import com.ljc.resource.server.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private ExecutorService executorService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private CartFeign cartFeign;
    @Autowired
    private MemberFeign memberFeign;
    @Autowired
    private WareFeign wareFeign;
    @Autowired
    private IOrderItemService orderItemService;
    @Autowired
    private ProductFeign productFeign;

    @Override
    public OrderConfirmVo confirm() throws ExecutionException, InterruptedException {
        OrderConfirmVo confirmVo = new OrderConfirmVo();
        Long userId = SecurityUtil.getUserId();
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        //开启第一个异步任务
        CompletableFuture<Void> addressFuture = CompletableFuture.runAsync(() -> {
//            1、远程查询所有的收获地址列表
            RequestContextHolder.setRequestAttributes(attributes);
            List<MemberAddressVo> address = memberFeign.get(userId).getData();
            confirmVo.setMemberAddressVos(address);
        }, executorService);

        //开启第二个异步任务
        CompletableFuture<Void> cartInfoFuture = CompletableFuture.runAsync(() -> {
            RequestContextHolder.setRequestAttributes(attributes);
            //2、远程查询购物车所有选中的购物项
            List<OrderItemVo> currentCartItems = cartFeign.list();
            confirmVo.setItems(currentCartItems);
            //feign在远程调用之前要构造请求，调用很多的拦截器
        }, executorService).thenRunAsync(() -> {
            RequestContextHolder.setRequestAttributes(attributes);
            List<OrderItemVo> items = confirmVo.getItems();
            //获取全部商品的id
            List<Long> skuIds = items.stream()
                    .map((OrderItemVo::getSkuId))
                    .collect(Collectors.toList());

            //远程查询商品库存信息
            List<SkuStock> skuStockList = wareFeign.getSkuStockList(skuIds).getData();

            if (skuStockList != null && skuStockList.size() > 0) {
                //将skuStockVos集合转换为map
                Map<Long, Boolean> skuHasStockMap = skuStockList.stream().collect(Collectors.toMap(SkuStock::getSkuId, SkuStock::getHasStock));
                confirmVo.setStocks(skuHasStockMap);
            }
        }, executorService);

        //3、查询用户积分
        Integer integration = SecurityUtil.getIntegration();
        confirmVo.setIntegration(integration);

        //4、价格数据自动计算

        //TODO 5、防重令牌(防止表单重复提交)
        //为用户设置一个token，三十分钟过期时间（存在redis）
        String token = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set("ljc:mall:order:token:" + userId, token, 30, TimeUnit.MINUTES);
        confirmVo.setOrderToken(token);
        CompletableFuture.allOf(addressFuture, cartInfoFuture).get();
        return confirmVo;
    }

    @Override
    @Transactional
    public Order submit(OrderSubmitVo vo) {
        Order responseVo = new Order();
        //去创建、下订单、验令牌、验价格、锁定库存...

        //获取当前用户登录的信息
        Long userId = SecurityUtil.getUserId();
        if (userId == null) {
            throw new RuntimeException("非法用户");
        }
        //1、验证令牌是否合法【令牌的对比和删除必须保证原子性】
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        String orderToken = vo.getOrderToken();

        //通过lure脚本原子验证令牌和删除令牌
        Long result = redisTemplate.execute(new DefaultRedisScript<>(script, Long.class),
                Collections.singletonList("ljc:mall:order:token:" + userId),
                orderToken);
        if (Objects.equals(result, 0L)) {
            throw new RuntimeException("重复提交订单");
        }
        //令牌验证成功
        //1、创建订单、订单项等信息
        OrderCreateTo order = createOrder(vo);

        //2、验证价格
        BigDecimal payAmount = order.getOrder().getPayAmount();
        BigDecimal payPrice = vo.getPayPrice();

        if (Math.abs(payAmount.subtract(payPrice).doubleValue()) < 0.01) {
            //金额对比
            //TODO 3、保存订单
            saveOrder(order);

            //4、库存锁定,只要有异常，回滚订单数据
            //订单号、所有订单项信息(skuId,skuNum,skuName)
            WareSkuLockVo lockVo = new WareSkuLockVo();
            lockVo.setOrderSn(order.getOrder().getOrderSn());

            //获取出要锁定的商品数据信息
            List<OrderItemVo> orderItemVos = order.getOrderItems().stream().map((item) -> {
                OrderItemVo orderItemVo = new OrderItemVo();
                orderItemVo.setSkuId(item.getSkuId());
                orderItemVo.setCount(item.getSkuQuantity());
                orderItemVo.setTitle(item.getSkuName());
                return orderItemVo;
            }).collect(Collectors.toList());
            lockVo.setLocks(orderItemVos);

            //TODO 调用远程锁定库存的方法
            //出现的问题：扣减库存成功了，但是由于网络原因超时，出现异常，导致订单事务回滚，库存事务不回滚(解决方案：seata)
            //为了保证高并发，不推荐使用seata，因为是加锁，并行化，提升不了效率,可以发消息给库存服务
            R<Object> r = wareFeign.orderLockStock(lockVo);
            if (r.isSuccess()) {
                //锁定成功
                // int i = 10/0;

                //TODO 订单创建成功，发送消息给MQ
//                rabbitTemplate.convertAndSend("order-event-exchange", "order.create.order", order.getOrder());

                //删除购物车里的数据
//                redisTemplate.delete(CART_PREFIX + memberResponseVo.getId());
                return order.getOrder();
            } else {
                throw new NoStockException(r.getErrorMessage());
            }

        } else {
            throw new RuntimeException("金额有误");
        }
    }

    private OrderCreateTo createOrder(OrderSubmitVo vo) {
        OrderCreateTo createTo = new OrderCreateTo();

        //1、生成订单号
        String orderSn = IdWorker.getTimeId();
        Order orderEntity = builderOrder(vo, orderSn);

        //2、获取到所有的订单项
        List<OrderItem> orderItemEntities = builderOrderItems(orderSn);

        //3、验价(计算价格、积分等信息)
        computePrice(orderEntity, orderItemEntities);

        createTo.setOrder(orderEntity);
        createTo.setOrderItems(orderItemEntities);

        return createTo;
    }

    private Order builderOrder(OrderSubmitVo vo, String orderSn) {

        //获取当前用户登录信息

        Order orderEntity = new Order();
        orderEntity.setMemberId(SecurityUtil.getUserId());
        orderEntity.setOrderSn(orderSn);
        orderEntity.setMemberUsername(SecurityUtil.getUsername());

        //远程获取收货地址和运费信息
        FareVo fareResp = wareFeign.getFare(vo.getAddrId()).getData();
//        FareVo fareResp = fareAddressVo.getData("data", new TypeReference<FareVo>() {});

        //获取到运费信息
        BigDecimal fare = fareResp.getFare();
        orderEntity.setFreightAmount(fare);

        //获取到收货地址信息
        MemberAddressVo address = fareResp.getAddress();
        //设置收货人信息
        orderEntity.setReceiverName(address.getName());
        orderEntity.setReceiverPhone(address.getPhone());
        orderEntity.setReceiverPostCode(address.getPostCode());
        orderEntity.setReceiverProvince(address.getProvince());
        orderEntity.setReceiverCity(address.getCity());
        orderEntity.setReceiverRegion(address.getRegion());
        orderEntity.setReceiverDetailAddress(address.getDetailAddress());

        //设置订单相关的状态信息
        orderEntity.setStatus(0);
        orderEntity.setAutoConfirmDay(7);
        orderEntity.setConfirmStatus(0);
        return orderEntity;
    }

    private List<OrderItem> builderOrderItems(String orderSn) {
        List<OrderItem> orderItemEntityList = new ArrayList<>();

        //最后确定每个购物项的价格
        List<OrderItemVo> currentCartItems = cartFeign.list();
        if (currentCartItems != null && currentCartItems.size() > 0) {
            orderItemEntityList = currentCartItems.stream().map((items) -> {
                //构建订单项数据
                OrderItem orderItemEntity = builderOrderItem(items);
                orderItemEntity.setOrderSn(orderSn);

                return orderItemEntity;
            }).collect(Collectors.toList());
        }

        return orderItemEntityList;
    }

    private OrderItem builderOrderItem(OrderItemVo items) {
        OrderItem orderItemEntity = new OrderItem();

        //1、商品的spu信息
        Long skuId = items.getSkuId();
        //获取spu的信息
        SpuInfoVo spuInfo = productFeign.getSpuInfoBySkuId(skuId).getData();
        orderItemEntity.setSpuId(spuInfo.getId());
        orderItemEntity.setSpuName(spuInfo.getSpuName());
        orderItemEntity.setSpuBrand(spuInfo.getBrandName());
        orderItemEntity.setCategoryId(spuInfo.getCatalogId());

        //2、商品的sku信息
        orderItemEntity.setSkuId(skuId);
        orderItemEntity.setSkuName(items.getTitle());
        orderItemEntity.setSkuPic(items.getImage());
        orderItemEntity.setSkuPrice(items.getPrice());
        orderItemEntity.setSkuQuantity(items.getCount());

        //使用StringUtils.collectionToDelimitedString将list集合转换为String
        String skuAttrValues = StringUtils.collectionToDelimitedString(items.getSkuAttrValues(), ";");
        orderItemEntity.setSkuAttrsVals(skuAttrValues);

        //3、商品的优惠信息

        //4、商品的积分信息
        orderItemEntity.setGiftGrowth(items.getPrice().multiply(new BigDecimal(items.getCount())).intValue());
        orderItemEntity.setGiftIntegration(items.getPrice().multiply(new BigDecimal(items.getCount())).intValue());

        //5、订单项的价格信息
        orderItemEntity.setPromotionAmount(BigDecimal.ZERO);
        orderItemEntity.setCouponAmount(BigDecimal.ZERO);
        orderItemEntity.setIntegrationAmount(BigDecimal.ZERO);

        //当前订单项的实际金额.总额 - 各种优惠价格
        //原来的价格
        BigDecimal origin = orderItemEntity.getSkuPrice().multiply(new BigDecimal(orderItemEntity.getSkuQuantity().toString()));
        //原价减去优惠价得到最终的价格
        BigDecimal subtract = origin.subtract(orderItemEntity.getCouponAmount())
                .subtract(orderItemEntity.getPromotionAmount())
                .subtract(orderItemEntity.getIntegrationAmount());
        orderItemEntity.setRealAmount(subtract);

        return orderItemEntity;
    }

    private void computePrice(Order orderEntity, List<OrderItem> orderItemEntities) {

        //总价
        BigDecimal total = new BigDecimal("0.0");
        //优惠价
        BigDecimal coupon = new BigDecimal("0.0");
        BigDecimal intergration = new BigDecimal("0.0");
        BigDecimal promotion = new BigDecimal("0.0");

        //积分、成长值
        Integer integrationTotal = 0;
        Integer growthTotal = 0;

        //订单总额，叠加每一个订单项的总额信息
        for (OrderItem orderItem : orderItemEntities) {
            //优惠价格信息
            coupon = coupon.add(orderItem.getCouponAmount());
            promotion = promotion.add(orderItem.getPromotionAmount());
            intergration = intergration.add(orderItem.getIntegrationAmount());

            //总价
            total = total.add(orderItem.getRealAmount());

            //积分信息和成长值信息
            integrationTotal += orderItem.getGiftIntegration();
            growthTotal += orderItem.getGiftGrowth();

        }
        //1、订单价格相关的
        orderEntity.setTotalAmount(total);
        //设置应付总额(总额+运费)
        orderEntity.setPayAmount(total.add(orderEntity.getFreightAmount()));
        orderEntity.setCouponAmount(coupon);
        orderEntity.setPromotionAmount(promotion);
        orderEntity.setIntegrationAmount(intergration);

        //设置积分成长值信息
        orderEntity.setIntegration(integrationTotal);
        orderEntity.setGrowth(growthTotal);

        //设置删除状态(0-未删除，1-已删除)
        orderEntity.setDeleteStatus(0);

    }

    private void saveOrder(OrderCreateTo orderCreateTo) {
        //获取订单信息
        Order order = orderCreateTo.getOrder();
        order.setModifyTime(LocalDateTime.now());
        order.setCreateTime(LocalDateTime.now());
        //保存订单
        this.baseMapper.insert(order);

        //获取订单项信息
        List<OrderItem> orderItems = orderCreateTo.getOrderItems();
        //批量保存订单项数据
        orderItemService.saveBatch(orderItems);
    }
}
