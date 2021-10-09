package com.ljc.common.exceptions;

import com.ljc.common.enums.ApiErrorCode;

public class NoStockException extends ApiException {

    public NoStockException() {
        super(ApiErrorCode.NO_STOCK_EXCEPTION);
    }

    public NoStockException(String message) {
        super(message);
    }

}
