package com.sangang.common.base.exception;

/**
 * @author sangang
 * @create 2023-05-24
 */
public class BusinessException extends RuntimeException {

    private int code = 0;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
