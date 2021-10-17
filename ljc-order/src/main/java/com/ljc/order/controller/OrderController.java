package com.ljc.order.controller;

import com.ljc.order.entity.Order;
import com.ljc.order.service.IOrderService;
import com.ljc.order.vo.OrderConfirmVo;
import com.ljc.order.vo.OrderSubmitVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping("/confirm")
    public OrderConfirmVo confirm () throws ExecutionException, InterruptedException {
        return orderService.confirm();
    }

    @PostMapping("/submit")
    public Order submit (OrderSubmitVo orderSubmitVo) {
        return orderService.submit(orderSubmitVo);
    }

}

