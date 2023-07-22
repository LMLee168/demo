package com.demo.common.manager;

import com.alibaba.fastjson.JSON;
import com.demo.common.manager.jdhandler.JdGoodsPromotiongoodsinfoQueryHandler;
import com.demo.common.manager.jdhandler.UnionOpenPromotionBysubunionidGetHandler;
import com.demo.common.manager.jdhandler.ZheTKUnionOpenPromotionBysubunionidGetHandler;
import com.demo.common.model.request.jd.PromotionCodeReq;
import com.demo.common.model.request.jd.UnionOpenGoodsPromotiongoodsinfoQueryRequest;
import com.demo.common.model.request.jd.UnionOpenPromotionBysubunionidGetRequest;
import com.demo.common.model.request.jd.ZheTaoKePromotionCodeRequest;
import com.demo.common.model.response.PromotionAgg;
import com.demo.common.model.response.jd.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * 1、解析淘口令获取商品id
 * 2、根据商品ID获取商品信息
 * 3、将商品转为专属的淘口令
 * 4、用户订单绑定
 */
@Slf4j
@Component
public class CommonJdManager {
    @Resource
    private JdGoodsPromotiongoodsinfoQueryHandler jdGoodsPromotiongoodsinfoQueryHandler;
    @Resource
    private UnionOpenPromotionBysubunionidGetHandler unionOpenPromotionBysubunionidGetHandler;
    @Resource
    private ZheTKUnionOpenPromotionBysubunionidGetHandler zheTKUnionOpenPromotionBysubunionidGetHandler;

    //SKUID查询推广商品 包含价格、佣金、类目等 不包含优惠券信息
    public List<PromotionGoodsResp> jdPromoteQueryWithSku(String skuIds) {
        UnionOpenGoodsPromotiongoodsinfoQueryRequest request = new UnionOpenGoodsPromotiongoodsinfoQueryRequest();
        request.setSkuIds(skuIds);
        PromotionQueryResult result = jdGoodsPromotiongoodsinfoQueryHandler.sendRequestWrapper(request);
        if (result.getCode() != 200) {
            log.error(result.getMessage());
            return null;
        }
        log.info("result {}", JSON.toJSONString(result));
        return result.getData();
    }

    //传入商品详情页链接  获得推广转链 （申请）
    public PromotionCodeResp getPromotionUrl(PromotionCodeReq req) {
        UnionOpenPromotionBysubunionidGetRequest request = new UnionOpenPromotionBysubunionidGetRequest();
        request.setPromotionCodeReq(req);
        PromotionCodeResp result = unionOpenPromotionBysubunionidGetHandler.sendRequestWrapper(request);
        if (result.getCode() != 200) {
            log.error(result.getMessage());
            return null;
        }
        return result;
    }


    //传入商品详情页链接  获得聚合推广信息 zhetaoke
    public PromotionCodeAggResp getPromoteAggByZhetaok(PromotionCodeReq req) {
        ZheTaoKePromotionCodeRequest request = new ZheTaoKePromotionCodeRequest();
        request.setApp_key("8bb75e5f298849a2901f19b2983708cc");
        request.setPromotionCodeReq(req);
        request.setSignurl("5");
        ZheTaoKeAggPromotionGetResponse result = zheTKUnionOpenPromotionBysubunionidGetHandler.sendRequestWrapper(request);
        if (result.getStatus() != 200) {
            log.error(result.getMessage());
            return null;
        }
        PromotionAgg aggInfo = result.getContent().get(0);
        PromotionCodeResp resp = new PromotionCodeResp();
        resp.setShortURL(aggInfo.getShorturl());
        resp.setClickURL(aggInfo.getCoupon_click_url());

        PromotionCodeAggResp aggResp = new PromotionCodeAggResp();
        aggResp.setPromotionCodeResp(resp);
        aggResp.setItem_url(aggInfo.getItem_url());
        aggResp.setTkfee3(aggInfo.getTkfee3());
        aggResp.setCoupon_id(aggInfo.getCoupon_id());
        aggResp.setQuanhou_jiage(aggInfo.getQuanhou_jiage());
        aggResp.setCoupon_info_money(aggInfo.getCoupon_info_money());
        aggResp.setSkuId(aggInfo.getTao_id());
        aggResp.setGoodsName(aggInfo.getTao_title());
        aggResp.setUnitPrice(new BigDecimal(aggInfo.getSize()));

        return aggResp;
    }

}
