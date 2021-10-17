package com.ljc.member.controller;


import com.ljc.member.entity.MemberReceiveAddress;
import com.ljc.member.service.IMemberReceiveAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 会员收货地址 前端控制器
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-16
 */
@RestController
@RequestMapping("/member/memberReceiveAddress")
public class MemberReceiveAddressController {

    @Autowired
    private IMemberReceiveAddressService memberReceiveAddressService;

    @GetMapping("/{memberId}")
    public List<MemberReceiveAddress> get(@PathVariable("memberId") Long memberId) {
        return memberReceiveAddressService.get(memberId);
    }

}

