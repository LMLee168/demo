package com.demo.common.model.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class WxTokenResponse {

    @JSONField(name = "access_token")
    private String accessToken;
    @JSONField(name = "expires_in")
    private Integer expiresIn;
    private  Integer errcode;

    private String errmsg;
}
