package com.ljc.order.service.impl;

import com.ljc.order.entity.OrderOperateHistory;
import com.ljc.order.mapper.OrderOperateHistoryMapper;
import com.ljc.order.service.IOrderOperateHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单操作历史记录 服务实现类
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-16
 */
@Service
public class OrderOperateHistoryServiceImpl extends ServiceImpl<OrderOperateHistoryMapper, OrderOperateHistory> implements IOrderOperateHistoryService {

}
