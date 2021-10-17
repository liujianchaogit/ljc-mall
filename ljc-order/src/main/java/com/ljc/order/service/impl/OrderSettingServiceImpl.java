package com.ljc.order.service.impl;

import com.ljc.order.entity.OrderSetting;
import com.ljc.order.mapper.OrderSettingMapper;
import com.ljc.order.service.IOrderSettingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单配置信息 服务实现类
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-16
 */
@Service
public class OrderSettingServiceImpl extends ServiceImpl<OrderSettingMapper, OrderSetting> implements IOrderSettingService {

}
