package com.ljc.common;

import com.ljc.common.advice.LjcAdvice;
import com.ljc.common.decoker.LjcDecoder;
import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Configuration
public class LjcAutoConfiguration {

    @Bean
    public ResponseBodyAdvice<Object> advice() {
        return new LjcAdvice();
    }

    @Bean
    public Decoder decoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        return new LjcDecoder(messageConverters);
    }

}
