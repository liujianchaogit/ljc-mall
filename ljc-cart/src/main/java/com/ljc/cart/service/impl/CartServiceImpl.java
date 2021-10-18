package com.ljc.cart.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.ljc.cart.fegin.ProductFeign;
import com.ljc.cart.po.Cart;
import com.ljc.cart.po.SkuInfo;
import com.ljc.cart.service.ICartService;
import com.ljc.resource.server.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ProductFeign productFeign;
    @Autowired
    private ExecutorService executorService;

    @Override
    public boolean add(Long skuId, Integer num) throws ExecutionException, InterruptedException {
        BoundHashOperations<String, Object, Object> cartOps = getCartOps();

        //判断Redis是否有该商品的信息
        String productRedisValue = (String) cartOps.get(skuId.toString());
        //如果没有就添加数据
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (StrUtil.isEmpty(productRedisValue)) {
            //2、添加新的商品到购物车(redis)
            Cart cart = new Cart();
            //开启第一个异步任务
            CompletableFuture<Void> getSkuInfoFuture = CompletableFuture.runAsync(() -> {
                //1、远程查询当前要添加商品的信息
                RequestContextHolder.setRequestAttributes(attributes);
                SkuInfo skuInfo = productFeign.getSkuInfo(skuId);
                //数据赋值操作
                cart.setSkuId(skuInfo.getSkuId());
                cart.setTitle(skuInfo.getSkuTitle());
                cart.setImage(skuInfo.getSkuDefaultImg());
                cart.setPrice(skuInfo.getPrice());
                cart.setCount(num);
            }, executorService);

            //开启第二个异步任务
            CompletableFuture<Void> getSkuAttrValuesFuture = CompletableFuture.runAsync(() -> {
                RequestContextHolder.setRequestAttributes(attributes);
                //2、远程查询skuAttrValues组合信息
                List<String> skuSaleAttrValues = productFeign.getSkuSaleAttrValues(skuId);
                cart.setSkuAttrValues(skuSaleAttrValues);
            }, executorService);

            //等待所有的异步任务全部完成
            CompletableFuture.allOf(getSkuInfoFuture, getSkuAttrValuesFuture).get();

            String cartItemJson = JSON.toJSONString(cart);
            cartOps.put(skuId.toString(), cartItemJson);

        } else {
            //购物车有此商品，修改数量即可
            Cart cartItemVo = JSON.parseObject(productRedisValue, Cart.class);
            cartItemVo.setCount(cartItemVo.getCount() + num);
            //修改redis的数据
            String cartItemJson = JSON.toJSONString(cartItemVo);
            cartOps.put(skuId.toString(), cartItemJson);
        }
        return true;
    }

    @Override
    public boolean delete(int[] skuId) {
        BoundHashOperations<String, Object, Object> cartOps = getCartOps();
        Long r = cartOps.delete(Arrays.stream(skuId).mapToObj(String::valueOf));
        return r != null && r > 0;
    }

    @Override
    public boolean update(String skuId, int checked) {
        BoundHashOperations<String, Object, Object> cartOps = getCartOps();

        String redisValue = (String) cartOps.get(skuId);

        Cart cart = JSON.parseObject(redisValue, Cart.class);

        //修改商品状态
        if (cart == null)
            throw new RuntimeException("该商品已被删除");
        cart.setCheck(checked == 1);
        cartOps.put(skuId,JSON.toJSONString(cart));
        return true;
    }

    @Override
    public List<Cart> list() {
        List<Cart> cartList = new ArrayList<>();
        //获取当前用户登录的信息
        Long userId = SecurityUtil.getUserId();
        //如果用户未登录直接返回null
        if (userId == null)
            return cartList;
        //获取购物车项
        String cartKey = "ljc:mall:cart:" + userId;
        //获取所有的
        List<Cart> cartItems = getCartItems(cartKey);
        if (cartItems == null) {
            return cartList;
        }
        //筛选出选中的
        cartList = cartItems.stream()
//                .filter(Cart::getCheck)
                .peek(item -> {
                    //更新为最新的价格（查询数据库）
                    BigDecimal price = productFeign.getPrice(item.getSkuId());
                    item.setPrice(price);
                })
                .collect(Collectors.toList());


        return cartList;
    }

    private List<Cart> getCartItems(String cartKey) {
        BoundHashOperations<String, Object, Object> operations = redisTemplate.boundHashOps(cartKey);
        List<Object> values = operations.values();
        if (values != null && values.size() > 0) {
            return values.stream().map((obj) -> {
                String str = (String) obj;
                return JSON.parseObject(str, Cart.class);
            }).collect(Collectors.toList());
        }
        return null;
    }

    private BoundHashOperations<String, Object, Object> getCartOps() {
        //先得到当前用户信息
        String cartKey = "ljc:mall:cart:" + SecurityUtil.getUserId();


        return redisTemplate.boundHashOps(cartKey);
    }
}
