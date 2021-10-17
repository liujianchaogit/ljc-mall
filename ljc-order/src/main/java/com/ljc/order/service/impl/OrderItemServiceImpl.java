package com.ljc.order.service.impl;

import com.ljc.order.entity.OrderItem;
import com.ljc.order.mapper.OrderItemMapper;
import com.ljc.order.service.IOrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单项信息 服务实现类
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-16
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements IOrderItemService {

}
