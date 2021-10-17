package com.ljc.cart.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ljc.cart.po.Cart;
import com.ljc.cart.service.ICartService;
import com.ljc.common.annotation.NoR;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RequestMapping("/cart")
@RestController
public class CartController {

    private final ICartService cartService;

    public CartController(ICartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public boolean add(@RequestParam Long skuId, @RequestParam Integer num) throws InterruptedException, ExecutionException, JsonProcessingException {
        return cartService.add(skuId, num);
    }

    @GetMapping("/list")
    public List<Cart> list() {
        return cartService.list();
    }

    @GetMapping("/listCart")
    @NoR
    public List<Cart> listCart() {
        return cartService.list();
    }

}
