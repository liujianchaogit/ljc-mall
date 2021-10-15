package com.ljc.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class LjcCartApplication {

    public static void main(String[] args) {
        SpringApplication.run(LjcCartApplication.class, args);
    }

}
