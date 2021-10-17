package com.ljc.ware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class LjcWareApplication {

    public static void main(String[] args) {
        SpringApplication.run(LjcWareApplication.class, args);
    }

}
