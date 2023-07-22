package com.demo.common.manager.tbhandler;

import com.alibaba.fastjson.JSON;
import com.demo.common.manager.SavvyBuyConfigEnum;
import com.demo.common.model.request.tb.WirelessShareTpwdQueryRequest;
import com.demo.common.model.response.tb.WirelessShareTpwdQueryResponse;
import com.demo.common.utils.TbSignUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class WirelessShareTpwdQueryHandler extends AbstractTbSavvyBuyHandler<WirelessShareTpwdQueryRequest, WirelessShareTpwdQueryResponse> {
    @Override
    protected Map<String, Object> buildRequest(WirelessShareTpwdQueryRequest request) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("method", getWorkWxApiEnum().getParamMethod());
        params.put("password_content", request.getPassword_content());
        TbSignUtil.getTbCommonItem(params);
        return params;
    }

    @Override
    protected WirelessShareTpwdQueryResponse buildResponse(String jsonResult) {
        return JSON.parseObject(jsonResult, WirelessShareTpwdQueryResponse.class);
    }

    @Override
    protected SavvyBuyConfigEnum getWorkWxApiEnum() {
        return SavvyBuyConfigEnum.SHARE_TPWD;
    }
}
