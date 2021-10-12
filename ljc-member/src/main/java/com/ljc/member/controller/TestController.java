package com.ljc.member.controller;

import com.ljc.resource.server.security.SecurityUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/a")
    public String a() {
        System.out.println(SecurityUtil.getUserId());
        System.out.println(SecurityUtil.getUsername());
        System.out.println(SecurityUtil.getClientId());
        return "123success";
    }

}
