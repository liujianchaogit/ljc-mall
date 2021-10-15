package com.ljc.auth.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class LjcAuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LjcAuthServerApplication.class, args);
    }

}
