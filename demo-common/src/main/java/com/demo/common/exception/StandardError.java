package com.demo.common.exception;

public interface StandardError {
    String getType();

    String getCode();

    String getDescription();

    String getToast();
}
