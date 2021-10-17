package com.ljc.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljc.member.entity.MemberReceiveAddress;
import com.ljc.member.mapper.MemberReceiveAddressMapper;
import com.ljc.member.service.IMemberReceiveAddressService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 会员收货地址 服务实现类
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-16
 */
@Service
public class MemberReceiveAddressServiceImpl extends ServiceImpl<MemberReceiveAddressMapper, MemberReceiveAddress> implements IMemberReceiveAddressService {

    @Override
    public List<MemberReceiveAddress> get(Long memberId) {
        return lambdaQuery().eq(MemberReceiveAddress::getMemberId, memberId).list();
    }
}
