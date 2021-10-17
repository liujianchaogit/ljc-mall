package com.ljc.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljc.order.entity.Order;
import com.ljc.order.vo.OrderConfirmVo;
import com.ljc.order.vo.OrderSubmitVo;

import java.util.concurrent.ExecutionException;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-16
 */
public interface IOrderService extends IService<Order> {

    OrderConfirmVo confirm() throws ExecutionException, InterruptedException;

    Order submit(OrderSubmitVo orderSubmitVo);
}
