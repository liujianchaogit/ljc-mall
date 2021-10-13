package com.ljc.common;

import com.ljc.common.advice.LjcAdvice;
import com.ljc.common.decoker.LjcDecoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LjcAutoConfiguration {

    @Bean
    public LjcAdvice ljcAdvice() {
        return new LjcAdvice();
    }

    @Bean
    public LjcDecoder ljcDecoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        return new LjcDecoder(messageConverters);
    }

}
