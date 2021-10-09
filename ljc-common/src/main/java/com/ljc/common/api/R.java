package com.ljc.common.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ljc.common.enums.ApiErrorCode;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private boolean success;
    private T data;
    private long errorCode;
    private String errorMessage;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private long total;

    public static <T> R<T> ok(T data) {
        ApiErrorCode aec = ApiErrorCode.SUCCESS;
        if (data instanceof Boolean && Boolean.FALSE.equals(data)) {
            aec = ApiErrorCode.FAILED;
        }
        return restResult(data, aec);
    }

    public static <T> R<T> ok(T data, long total) {
        return restResult(data, ApiErrorCode.SUCCESS.getCode(), ApiErrorCode.SUCCESS.getMsg(), total);
    }

    public static <T> R<T> failed(String errorMessage) {
        return restResult(null, ApiErrorCode.FAILED.getCode(), errorMessage, 0);
    }

    public static <T> R<T> failed(IErrorCode errorCode) {
        return restResult(null, errorCode);
    }

    private static <T> R<T> restResult(T data, IErrorCode errorCode) {
        return restResult(data, errorCode.getCode(), errorCode.getMsg(), 0);
    }

    private static <T> R<T> restResult(T data, long errorCode, String errorMessage, long total) {
        R<T> apiResult = new R<>();
        apiResult.setSuccess(ApiErrorCode.SUCCESS.getCode() == errorCode);
        apiResult.setData(data);
        apiResult.setErrorCode(errorCode);
        apiResult.setErrorMessage(errorMessage);
        apiResult.setTotal(total);
        return apiResult;
    }

}
