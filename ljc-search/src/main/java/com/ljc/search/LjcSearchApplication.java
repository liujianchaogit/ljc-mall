package com.ljc.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class LjcSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(LjcSearchApplication.class, args);
    }

}
