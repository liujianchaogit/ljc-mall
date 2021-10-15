package com.ljc.common;

import com.ljc.common.advice.Advice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LjcAutoConfiguration {

    @Bean
    public Advice advice() {
        return new Advice();
    }

}
