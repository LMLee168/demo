package com.demo.common.manager.tbhandler;

import com.alibaba.fastjson.JSON;
import com.demo.common.manager.SavvyBuyConfigEnum;
import com.demo.common.model.request.tb.TbkScInvitecodeGetRequest;
import com.demo.common.model.response.tb.TbkScInvitecodeGetResponse;
import com.demo.common.utils.TbSignUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 私域用户邀请码生成 需用户授权
 *私域用户管理(即渠道管理或会员运营管理)功能中，通过此API可生成淘宝客自身的邀请码。
 */
@Component
@Slf4j
public class TbkScInvitecodeGetHandler extends AbstractTbSavvyBuyHandler<TbkScInvitecodeGetRequest, TbkScInvitecodeGetResponse> {

    @Override
    protected Map<String, Object> buildRequest(TbkScInvitecodeGetRequest request) {
        // 业务参数
        Map<String, Object> params = Maps.newHashMap();
        params.put("method", getWorkWxApiEnum().getParamMethod());
        if (request.getRelationId() != null) {
            params.put("ralation_id", request.getRelationId().toString());
        }
        params.put("ralation_app", request.getRalationApp());
        params.put("method", getWorkWxApiEnum().getParamMethod());
        params.put("code_type", request.getCodeType().toString());
        TbSignUtil.getTbCommonItem(params);
        return params;
    }

    @Override
    protected TbkScInvitecodeGetResponse buildResponse(String jsonResult) {
        return JSON.parseObject(jsonResult, TbkScInvitecodeGetResponse.class);
    }

    @Override
    protected SavvyBuyConfigEnum getWorkWxApiEnum() {
        return SavvyBuyConfigEnum.INVITECODE;
    }
}
