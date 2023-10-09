package com.demo.common.model.bo;

import com.demo.common.utils.SmartStringStyle;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

public class BaseDO implements Serializable {
    private static final long serialVersionUID = 3573117284488694934L;

    public BaseDO(){}

    public String toString() {
        return ReflectionToStringBuilder.toString(this, SmartStringStyle.getInstance());
    }

}
