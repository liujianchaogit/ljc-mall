package com.ljc.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class LjcAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(LjcAuthApplication.class, args);
    }

}
