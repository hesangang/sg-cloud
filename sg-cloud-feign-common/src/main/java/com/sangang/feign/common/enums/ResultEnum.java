package com.sangang.feign.common.enums;

import com.sangang.feign.common.constant.IErrorCode;

/**
 * sangang
 */
public enum ResultEnum implements IErrorCode {
    SUCCESS(200, "操作成功"),
    FAILED(500, "服务端异常"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "未登录或已过期"),
    FORBIDDEN(403, "没有相关权限");

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
