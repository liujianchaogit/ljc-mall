package com.ljc.resource.server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class T {
    @RequestMapping(value = "/a", method = {RequestMethod.GET, RequestMethod.POST})
    public String dsgf(Principal principal) {
        if (principal != null)
            System.out.println(principal.getName());
        return "successsssssssssssssss";
    }
}
