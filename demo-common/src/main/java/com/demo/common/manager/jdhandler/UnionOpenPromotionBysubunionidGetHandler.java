package com.demo.common.manager.jdhandler;

import com.alibaba.fastjson.JSON;
import com.demo.common.manager.SavvyBuyConfigEnum;
import com.demo.common.model.request.jd.UnionOpenPromotionBysubunionidGetRequest;
import com.demo.common.model.response.jd.PromotionCodeResp;
import com.demo.common.model.response.jd.QueryHttpResponse;
import com.demo.common.utils.TbSignUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 传入商品详情页链接  获得推广转链 （申请）
 */
@Slf4j
@Component
public class UnionOpenPromotionBysubunionidGetHandler extends AbstractJdSavvyBuyHandler<UnionOpenPromotionBysubunionidGetRequest, PromotionCodeResp>{
    @Override
    protected Map<String, Object> buildRequest(UnionOpenPromotionBysubunionidGetRequest request) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("promotionCodeReq", request.getPromotionCodeReq());
        return TbSignUtil.getJdCommonItem(params, getWorkWxApiEnum().getParamMethod());
    }

    @Override
    protected PromotionCodeResp buildResponse(String jsonResult) {
        QueryHttpResponse queryHttpResponse = JSON.parseObject(jsonResult, QueryHttpResponse.class);
        if (queryHttpResponse.getError_response() != null) {
            PromotionCodeResp resp = new PromotionCodeResp();
            resp.setCode(queryHttpResponse.getError_response().getCode());
            resp.setMessage(queryHttpResponse.getError_response().getZh_desc());
            return resp;
        }
        return JSON.parseObject(queryHttpResponse.getJd_union_open_promotion_bysubunionid_get_response().getGetResult(), PromotionCodeResp.class);
    }

    @Override
    protected SavvyBuyConfigEnum getWorkWxApiEnum() {
        return SavvyBuyConfigEnum.PROMOTION_BYSUBUNIONID;
    }
}
