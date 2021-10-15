package com.ljc.cart.service.impl;

import cn.hutool.core.util.StrUtil;
import com.ljc.cart.fegin.ProductFeign;
import com.ljc.cart.service.ICartService;
import com.ljc.resource.server.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ProductFeign productFeign;
    @Autowired
    private ExecutorService executorService;

    @Override
    public boolean add(Long skuId, Integer num) {
        BoundHashOperations<String, Object, Object> cartOps = getCartOps();

        //判断Redis是否有该商品的信息
        String productRedisValue = (String) cartOps.get(skuId.toString());
        //如果没有就添加数据
//        if (StrUtil.isEmpty(productRedisValue)) {
//
//            //2、添加新的商品到购物车(redis)
//            CartItemVo cartItemVo = new CartItemVo();
//            //开启第一个异步任务
//            CompletableFuture<Void> getSkuInfoFuture = CompletableFuture.runAsync(() -> {
//                //1、远程查询当前要添加商品的信息
//                R productSkuInfo = productFeignService.getInfo(skuId);
//                SkuInfoVo skuInfo = productSkuInfo.getData("skuInfo", new TypeReference<SkuInfoVo>() {});
//                //数据赋值操作
//                cartItemVo.setSkuId(skuInfo.getSkuId());
//                cartItemVo.setTitle(skuInfo.getSkuTitle());
//                cartItemVo.setImage(skuInfo.getSkuDefaultImg());
//                cartItemVo.setPrice(skuInfo.getPrice());
//                cartItemVo.setCount(num);
//            }, executorService);
//
//            //开启第二个异步任务
//            CompletableFuture<Void> getSkuAttrValuesFuture = CompletableFuture.runAsync(() -> {
//                //2、远程查询skuAttrValues组合信息
//                List<String> skuSaleAttrValues = productFeignService.getSkuSaleAttrValues(skuId);
//                cartItemVo.setSkuAttrValues(skuSaleAttrValues);
//            }, executorService);
//
//            //等待所有的异步任务全部完成
//            CompletableFuture.allOf(getSkuInfoFuture, getSkuAttrValuesFuture).get();
//
//            String cartItemJson = JSON.toJSONString(cartItemVo);
//            cartOps.put(skuId.toString(), cartItemJson);
//
//            return cartItemVo;
//        } else {
//            //购物车有此商品，修改数量即可
//            CartItemVo cartItemVo = JSON.parseObject(productRedisValue, CartItemVo.class);
//            cartItemVo.setCount(cartItemVo.getCount() + num);
//            //修改redis的数据
//            String cartItemJson = JSON.toJSONString(cartItemVo);
//            cartOps.put(skuId.toString(), cartItemJson);
//
//            return cartItemVo;
//        }
        return false;
    }


    private BoundHashOperations<String, Object, Object> getCartOps() {
        //先得到当前用户信息
        String cartKey = "ljc:mall:cart:" + SecurityUtil.getUserId();


        return redisTemplate.boundHashOps(cartKey);
    }
}
