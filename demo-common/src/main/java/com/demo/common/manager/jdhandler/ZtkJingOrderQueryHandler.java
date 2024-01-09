package com.demo.common.manager.jdhandler;

import com.alibaba.fastjson.JSON;
import com.demo.common.manager.SavvyBuyConfigEnum;
import com.demo.common.model.request.jd.ZtkJingOrderQueryRequest;
import com.demo.common.model.response.jd.ZheTaoKeAggPromotionGetResponse;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 新订单查询api
 */
@Component
public class ZtkJingOrderQueryHandler extends  AbstractJdSavvyBuyHandler<ZtkJingOrderQueryRequest, ZheTaoKeAggPromotionGetResponse>{
    @Override
    protected Map<String, Object> buildRequest(ZtkJingOrderQueryRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("appKey", request.getKey());
        map.put("jd_app_key", request.getJd_app_key());
        map.put("jd_app_secret",request.getJd_app_secret());
        map.put("pageIndex",request.getPageIndex());
        map.put("pageSize", request.getPageSize());
        map.put("type", request.getType());
        map.put("startTime",request.getStartTime());
        map.put("endTime", request.getEndTime());
        map.put("childUnionId", request.getChildUnionId());
        map.put("key", request.getKey());
        map.put("fields", request.getFields());
        return map;
    }

    @Override
    protected ZheTaoKeAggPromotionGetResponse buildResponse(String jsonResult) {
        return JSON.parseObject(jsonResult, ZheTaoKeAggPromotionGetResponse.class);
    }

    @Override
    protected SavvyBuyConfigEnum getWorkWxApiEnum() {
        return SavvyBuyConfigEnum.ZTK_JING_ORDER_QUERY;
    }
}
