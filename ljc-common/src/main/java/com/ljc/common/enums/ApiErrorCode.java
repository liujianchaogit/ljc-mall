package com.ljc.common.enums;

import com.ljc.common.api.IErrorCode;

public enum ApiErrorCode implements IErrorCode {

    FAILED(-1, "操作失败"),
    SUCCESS(0, "执行成功"),
    UNKNOW_EXCEPTION(10000, "系统未知异常"),
    VAILD_EXCEPTION(10001, "参数格式校验失败"),
    TO_MANY_REQUEST(10002, "请求流量过大，请稍后再试"),
    SMS_CODE_EXCEPTION(10002, "验证码获取频率太高，请稍后再试"),
    PRODUCT_UP_EXCEPTION(11000, "商品上架异常"),
    USER_EXIST_EXCEPTION(15001, "存在相同的用户"),
    PHONE_EXIST_EXCEPTION(15002, "存在相同的手机号"),
    NO_STOCK_EXCEPTION(21000, "商品库存不足"),
    LOGINACCT_PASSWORD_EXCEPTION(15003, "账号或密码错误");

    private final long code;
    private final String msg;

    ApiErrorCode(final long code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ApiErrorCode fromCode(long code) {
        ApiErrorCode[] ecs = ApiErrorCode.values();
        for (ApiErrorCode ec : ecs) {
            if (ec.getCode() == code) {
                return ec;
            }
        }
        return SUCCESS;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return String.format(" ErrorCode:{code=%s, msg=%s} ", code, msg);
    }

}
