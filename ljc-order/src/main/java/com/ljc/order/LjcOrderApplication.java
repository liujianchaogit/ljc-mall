package com.ljc.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class LjcOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(LjcOrderApplication.class, args);
    }

}
