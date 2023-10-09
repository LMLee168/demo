package com.demo.common.manager.jdhandler;

import com.alibaba.fastjson.JSON;
import com.demo.common.manager.SavvyBuyConfigEnum;
import com.demo.common.model.request.jd.ZheTaoKePromotionCodeRequest;
import com.demo.common.model.response.jd.ZheTaoKeAggPromotionGetResponse;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ZheTKUnionOpenPromotionBysubunionidGetHandler extends  AbstractJdSavvyBuyHandler<ZheTaoKePromotionCodeRequest, ZheTaoKeAggPromotionGetResponse>{
    @Override
    protected Map<String, Object> buildRequest(ZheTaoKePromotionCodeRequest request) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("appKey", "8bb75e5f298849a2901f19b2983708cc");
        params.put("materialId", request.getPromotionCodeReq().getMaterialId());
        params.put("unionId", request.getPromotionCodeReq().getUnionId());
        params.put("positionId", request.getPromotionCodeReq().getPositionId());
        params.put("pid", request.getPromotionCodeReq().getPid());
        params.put("couponUrl", request.getPromotionCodeReq().getCouponUrl());
        params.put("subUnionId", request.getPromotionCodeReq().getSubUnionId());
        params.put("chainType", request.getPromotionCodeReq().getChainType());
        params.put("giftCouponKey", request.getPromotionCodeReq().getGiftCouponKey());
        params.put("signurl", request.getSignurl());
        return params;
    }

    @Override
    protected ZheTaoKeAggPromotionGetResponse buildResponse(String jsonResult) {
        return JSON.parseObject(jsonResult, ZheTaoKeAggPromotionGetResponse.class);
    }

    @Override
    protected SavvyBuyConfigEnum getWorkWxApiEnum() {
        return SavvyBuyConfigEnum.ZETAOKE_PROMOTION_BYSUBUNIONID;
    }
}
