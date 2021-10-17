package com.ljc.member.controller;

import com.ljc.common.dto.auth.User;
import com.ljc.member.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/member")
@RestController
public class MemberController {

    @Autowired
    private IMemberService memberService;

    @GetMapping("/login")
    public User login(@RequestParam String username) {
        return memberService.loadUserByUsername(username);
    }


}

