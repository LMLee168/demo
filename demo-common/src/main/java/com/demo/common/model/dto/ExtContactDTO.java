package com.demo.common.model.dto;

import com.demo.common.model.bo.BaseDO;
import lombok.Data;

@Data
public class ExtContactDTO extends BaseDO {
    private String external_userid;
    private String name;
    private Integer type;
    private String avatar;
    private Integer gender;
    private String unionid;
}
