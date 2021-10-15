package com.ljc.common;

import com.ljc.common.advice.Advice;
import com.ljc.common.config.ThreadPoolConfigProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
@EnableConfigurationProperties(ThreadPoolConfigProperties.class)
public class LjcAutoConfiguration {

    @Bean
    public Advice advice() {
        return new Advice();
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
