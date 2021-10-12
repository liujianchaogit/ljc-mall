package com.ljc.common.advice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ljc.common.api.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice("*.controller")
@Slf4j
public class LjcAdvice implements ResponseBodyAdvice<Object> {

    @ExceptionHandler
    public R<String> exceptionHandler(Exception e) {
        log.error(e.getMessage());
        return R.failed(e.getMessage());
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof String || body instanceof R) {
            return body;
        }
        if (body instanceof IPage) {
            return R.ok(((IPage) body).getRecords(), ((IPage) body).getTotal());
        }
        return R.ok(body);
    }

}
