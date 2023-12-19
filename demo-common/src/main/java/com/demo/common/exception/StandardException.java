package com.demo.common.exception;

public class StandardException extends RuntimeException implements StandardError {
    private static final long serialVersionUID = -4762628230324425726L;
    private String type;
    private String code;
    private String description;
    private String toast;

    public StandardException() {
    }

    public StandardException(StandardError error, String description) {
        this.type = error.getType();
        this.code = error.getCode();
        this.description = description;
    }

    public StandardException(String description) {
        this.description = description;
    }

    public StandardException(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public StandardException(String code, String message, String toast) {
        this.code = code;
        this.description = message;
        this.toast = toast;
    }

    @Override
    public String getType() {
        return this.type;
    }

    public StandardException setType(String type) {
        this.type = type;
        return this;
    }
    @Override
    public String getCode() {
        return this.code;
    }

    public StandardException setCode(String code) {
        this.code = code;
        return this;
    }
    @Override
    public String getDescription() {
        return this.description;
    }

    public StandardException setDescription(String description) {
        this.description = description;
        return this;
    }
    @Override
    public String getToast() {
        return this.toast;
    }

    public void setToast(String toast) {
        this.toast = toast;
    }
    @Override
    public String toString() {
        return "{type:" + this.type + ",code:" + this.code + ",description:" + this.description + "}";
    }
    @Override
    public String getMessage() {
        return this.toString();
    }
}

