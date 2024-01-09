package com.demo.common.manager.dyhandler;

import com.alibaba.fastjson.JSON;
import com.demo.common.manager.SavvyBuyConfigEnum;
import com.demo.common.model.request.dy.ProductShareRequest;
import com.demo.common.model.response.dy.ProductShareResponse;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 分销转链  商品URL/口令/短链接
 */

@Slf4j
@Component
public class ProductShareHandler extends AbstractDySavvyBuyHandler<ProductShareRequest, ProductShareResponse>{
    @Override
    protected Map<String, Object> buildRequest(ProductShareRequest request) {
        Map<String, Object> params = Maps.newHashMap();
//        params.put("skuIds", );
        return null;
    }

    @Override
    protected ProductShareResponse buildResponse(String jsonResult) {
        return JSON.parseObject(jsonResult, ProductShareResponse.class);
    }

    @Override
    protected SavvyBuyConfigEnum getWorkWxApiEnum() {
        return SavvyBuyConfigEnum.ZTK_DY_PRODUCT_SHARE;
    }
}
