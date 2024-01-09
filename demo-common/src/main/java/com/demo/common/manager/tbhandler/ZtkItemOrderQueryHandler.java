package com.demo.common.manager.tbhandler;

import com.alibaba.fastjson.JSON;
import com.demo.common.manager.SavvyBuyConfigEnum;
import com.demo.common.model.request.tb.TbkItemOrderZtkQueryRequest;
import com.demo.common.model.response.tb.TbkItemOrderZtkQueryResponse;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class ZtkItemOrderQueryHandler extends AbstractTbSavvyBuyHandler<TbkItemOrderZtkQueryRequest, TbkItemOrderZtkQueryResponse>{
    @Override
    protected Map<String, Object> buildRequest(TbkItemOrderZtkQueryRequest request) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("appKey", "8bb75e5f298849a2901f19b2983708cc");
        params.put("sid", request.getSid());
        params.put("orderid", request.getOrderid());
        params.put("page", request.getPage());
        params.put("page_size", request.getPage_size());
        params.put("startTime", request.getStartTime());
        params.put("endTime",request.getEndTime());
        params.put("type", request.getType());
        params.put("san_pingtai_id", request.getSan_pingtai_id());
        return params;
    }

    @Override
    protected TbkItemOrderZtkQueryResponse buildResponse(String jsonResult) {
        return JSON.parseObject(jsonResult, TbkItemOrderZtkQueryResponse.class);
    }

    @Override
    protected SavvyBuyConfigEnum getWorkWxApiEnum() {
        return SavvyBuyConfigEnum.ZTK_TB_ITEM_QUERY_ORDER;
    }
}
