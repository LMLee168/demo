package com.demo.common.model.response.tb;

import com.demo.common.model.response.TbResultResponse;
import lombok.Data;

@Data
public class TbkScPublisherInfoSaveResponse extends TbResultResponse {

    /**
     * 渠道关系id
     */
    private Integer relationId;
    /**
     * 渠道昵称
     */
    private String accountName;
    /**
     * 会员运营id
     */
    private Integer specialId;
    /**
     * 绑定成功  如果重复绑定会提示："重复绑定渠道" / "重复绑定粉丝"
     */
    private String desc;
}
