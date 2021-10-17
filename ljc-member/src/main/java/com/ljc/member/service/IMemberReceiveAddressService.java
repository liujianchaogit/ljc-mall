package com.ljc.member.service;

import com.ljc.member.entity.MemberReceiveAddress;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 会员收货地址 服务类
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-16
 */
public interface IMemberReceiveAddressService extends IService<MemberReceiveAddress> {

    List<MemberReceiveAddress> get(Long memberId);
}
