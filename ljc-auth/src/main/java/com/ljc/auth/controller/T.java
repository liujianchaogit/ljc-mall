package com.ljc.auth.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/b")
public class T {

    @PostMapping("/a")
    public String sf(Principal principal) {
        if (principal == null)
            return "null";
        return "success " + principal.getName();
    }
}
/*
<Button onClick={() => {
          request('/oauth/token', {
            method: 'post',
            headers: {
              Authorization: 'Basic Y2xpZW50XzI6MTIzNDU2'
            },
            params: {
              grant_type: 'password',
              username: 'user_1',
              password: '123456'
            }
          })
        }}>asd</Button>
 */