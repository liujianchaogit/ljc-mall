package com.ljc.resource.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "ljc.security")
public class SecurityProperties {

    private List<String> anonymousUrls = new ArrayList<>();
    private List<String> ignoreUrls = new ArrayList<>();

    public List<String> getAnonymousUrls() {
        return anonymousUrls;
    }

    public void setAnonymousUrls(List<String> anonymousUrls) {
        this.anonymousUrls = anonymousUrls;
    }

    public List<String> getIgnoreUrls() {
        return ignoreUrls;
    }

    public void setIgnoreUrls(List<String> ignoreUrls) {
        this.ignoreUrls = ignoreUrls;
    }

}
