package com.demo.common.encrypt;

import com.demo.common.enumation.PrivacyTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PrivacyEncrypt {
    PrivacyTypeEnum type();

    int prefixNoMaskLen() default 1;

    int suffixNoMaskLen() default 1;

    String symbol() default "*";
}
