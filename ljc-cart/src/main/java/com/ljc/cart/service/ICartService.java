package com.ljc.cart.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ljc.cart.po.Cart;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface ICartService {

    boolean add(Long skuId, Integer num) throws ExecutionException, InterruptedException, JsonProcessingException;

    List<Cart> list();
}
