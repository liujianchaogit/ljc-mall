package com.ljc.common;

import com.ljc.common.advice.LjcAdvice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LjcAutoConfiguration {

    @Bean
    public LjcAdvice ljcAdvice() {
        return new LjcAdvice();
    }

}
