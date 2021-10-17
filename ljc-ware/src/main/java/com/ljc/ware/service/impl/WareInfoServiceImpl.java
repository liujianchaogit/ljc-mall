package com.ljc.ware.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljc.common.dto.ware.FareVo;
import com.ljc.common.dto.ware.MemberAddressVo;
import com.ljc.ware.entity.WareInfo;
import com.ljc.ware.feign.MemberFeign;
import com.ljc.ware.mapper.WareInfoMapper;
import com.ljc.ware.service.IWareInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WareInfoServiceImpl extends ServiceImpl<WareInfoMapper, WareInfo> implements IWareInfoService {

    @Autowired
    private MemberFeign memberFeign;

    @Override
    public FareVo getFare(Long addrId) {
        FareVo fareVo = new FareVo();
        MemberAddressVo memberAddressVo = memberFeign.info(addrId).getData();
        String phone = memberAddressVo.getPhone();
        //截取用户手机号码最后一位作为我们的运费计算
        //1558022051
        String fare = phone.substring(phone.length() - 10, phone.length() - 8);
        BigDecimal bigDecimal = new BigDecimal(fare);

        fareVo.setFare(bigDecimal);
        fareVo.setAddress(memberAddressVo);

        return fareVo;
    }
}
