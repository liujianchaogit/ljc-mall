package com.ljc.cart.controller;

import com.ljc.cart.service.ICartService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/cart")
@RestController
public class CartController {

    private final ICartService cartService;

    public CartController(ICartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/add")
    public boolean add(@RequestParam("skuId") Long skuId, @RequestParam("num") Integer num) {
        return cartService.add(skuId, num);
    }

}
