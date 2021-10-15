package com.ljc.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ljc.thread")
@Data
public class ThreadPoolConfigProperties {

    private int coreSize = 10;
    private int maxSize = 20;
    private int keepAliveTime = 3600;


}
