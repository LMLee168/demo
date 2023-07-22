package com.demo.common.model.response.jd;

import com.demo.common.model.bo.BaseDO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorRessponse extends BaseDO {

    private Integer code;
    private String zh_desc;
}
