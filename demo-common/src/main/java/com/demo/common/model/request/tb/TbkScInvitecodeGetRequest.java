package com.demo.common.model.request.tb;

import com.demo.common.model.request.SavvyBuyRequest;
import lombok.Data;

@Data
public class TbkScInvitecodeGetRequest extends SavvyBuyRequest {
    /**
     * 渠道关系
     */
    private Integer relationId;
    /**
     * 推广的物料类型  common
     */
    private String ralationApp;
    /**
     * 1-渠道邀请 2-渠道裂变 3-会员邀请
     */
    private Integer codeType;
}
