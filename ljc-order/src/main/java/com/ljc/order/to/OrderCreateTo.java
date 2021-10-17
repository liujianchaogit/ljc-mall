package com.ljc.order.to;

import com.ljc.order.entity.Order;
import com.ljc.order.entity.OrderItem;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


@Data
public class OrderCreateTo {

    private Order order;

    private List<OrderItem> orderItems;

    /** 订单计算的应付价格 **/
    private BigDecimal payPrice;

    /** 运费 **/
    private BigDecimal fare;

}
