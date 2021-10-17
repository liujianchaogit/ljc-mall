package com.ljc.common;

import com.ljc.common.advice.Advice;
import com.ljc.common.config.ThreadPoolConfigProperties;
import com.ljc.common.interceptor.AuthorizationRequestInterceptor;
import feign.RequestInterceptor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.*;

@Configuration
@EnableConfigurationProperties(ThreadPoolConfigProperties.class)
public class LjcAutoConfiguration {

    @Bean
    public Advice advice() {
        return new Advice();
    }

    @Bean
    public RequestInterceptor interceptor(HttpServletRequest request) {
        return new AuthorizationRequestInterceptor(request);
    }

    @Bean
    public ExecutorService executorService(ThreadPoolConfigProperties threadPoolConfigProperties) {
        return new ThreadPoolExecutor(
                threadPoolConfigProperties.getCoreSize(),
                threadPoolConfigProperties.getMaxSize(),
                threadPoolConfigProperties.getKeepAliveTime(),
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(100000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

}
