package com.ljc.member.controller;


import com.ljc.common.dto.UserDto;
import com.ljc.member.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-12
 */
@RestController
@RequestMapping("/member/member")
public class MemberController {

    @Autowired
    private IMemberService memberService;

    @GetMapping("/loadByUsername")
    public UserDto loadUserByUsername(@RequestParam String username) {
        return memberService.loadUserByUsername(username);
    }


}

