package com.ljc.cart.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ljc.cart.po.Cart;
import com.ljc.cart.service.ICartService;
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

    @DeleteMapping(value = "/delete")
    public boolean deleteItem(@RequestParam("skuIds") int[] skuIds) {
        return cartService.delete(skuIds);
    }

    @PutMapping(value = "/update")
    public boolean checkItem(String skuId, int checked) {
        return cartService.update(skuId, checked);
    }

    @GetMapping("/list")
    public List<Cart> list() {
        return cartService.list();
    }

}
