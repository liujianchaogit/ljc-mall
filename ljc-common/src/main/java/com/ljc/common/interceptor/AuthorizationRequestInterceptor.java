package com.ljc.common.interceptor;

import cn.hutool.core.util.StrUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;

public class AuthorizationRequestInterceptor implements RequestInterceptor {

    private final HttpServletRequest request;

    public AuthorizationRequestInterceptor(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void apply(RequestTemplate template) {
        if (StrUtil.isNotEmpty(request.getHeader(HttpHeaders.AUTHORIZATION)))
            template.header(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));
    }

}
