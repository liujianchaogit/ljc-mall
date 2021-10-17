package com.ljc.order.service.impl;

import com.ljc.order.entity.PaymentInfo;
import com.ljc.order.mapper.PaymentInfoMapper;
import com.ljc.order.service.IPaymentInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付信息表 服务实现类
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-16
 */
@Service
public class PaymentInfoServiceImpl extends ServiceImpl<PaymentInfoMapper, PaymentInfo> implements IPaymentInfoService {

}
