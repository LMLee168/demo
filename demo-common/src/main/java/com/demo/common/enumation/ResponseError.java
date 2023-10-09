package com.demo.common.enumation;

public enum ResponseError {
    SYSTEM_ERROR("100000", "系统错误", ""),
    ;


    private final String code;
    private final String message;
    private final String toast;

    private ResponseError(String code, String message, String toast) {
        this.code = code;
        this.message = message;
        this.toast = toast;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getToast() {
        return this.toast;
    }
}
