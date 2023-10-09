package com.demo.common.model.response.tb;

import com.alibaba.fastjson.annotation.JSONField;
import com.demo.common.model.response.TbResultResponse;
import lombok.Data;

@Data
public class TbkScInvitecodeGetResponse extends TbResultResponse {

    @JSONField(name = "inviter_code")
    private String inviterCode;
}
