package com.demo.common.manager.jdhandler;

import com.alibaba.fastjson.JSON;
import com.demo.common.manager.SavvyBuyConfigEnum;
import com.demo.common.model.request.jd.UnionOpenGoodsPromotiongoodsinfoQueryRequest;
import com.demo.common.model.response.jd.PromotionQueryResult;
import com.demo.common.model.response.jd.QueryHttpResponse;
import com.demo.common.utils.TbSignUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 获取推广物料类:
 * 通过SKUID查询推广商品的名称、主图、类目、价格、物流、是否自营、30天引单数量等详细信息，支持批量获取。通常用于在媒体侧展示商品详情。
 * 20230601 直接可调用
 */
@Slf4j
@Component
public class JdGoodsPromotiongoodsinfoQueryHandler extends AbstractJdSavvyBuyHandler<UnionOpenGoodsPromotiongoodsinfoQueryRequest, PromotionQueryResult> {
    @Override
    protected Map<String, Object> buildRequest(UnionOpenGoodsPromotiongoodsinfoQueryRequest request) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("skuIds", request.getSkuIds());
        return TbSignUtil.getJdCommonItem(params, getWorkWxApiEnum().getParamMethod());
    }

    @Override
    protected PromotionQueryResult buildResponse(String jsonResult) {
        QueryHttpResponse queryHttpResponse = JSON.parseObject(jsonResult, QueryHttpResponse.class);
        if (queryHttpResponse.getError_response() != null) {
            PromotionQueryResult resp = new PromotionQueryResult();
            resp.setCode(queryHttpResponse.getError_response().getCode());
            resp.setMessage(queryHttpResponse.getError_response().getZh_desc());
            return resp;
        }
        return JSON.parseObject(queryHttpResponse.getJd_union_open_goods_promotiongoodsinfo_query_responce().getQueryResult(), PromotionQueryResult.class);
    }

    @Override
    protected SavvyBuyConfigEnum getWorkWxApiEnum() {
        return SavvyBuyConfigEnum.PROMOTION_GOODS_INFO;
    }
}
