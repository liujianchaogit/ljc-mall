package com.ljc.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/a")
    public String a(Principal principal) {
        if (principal == null) return "successs";
        return principal.getName();
    }

}
