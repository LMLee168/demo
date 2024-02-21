package unicorn.mp.common.manager;

import com.alibaba.fastjson.JSON;
import unicorn.mp.common.exception.StandardException;
import unicorn.mp.common.manager.jdhandler.JdGoodsPromotiongoodsinfoQueryHandler;
import unicorn.mp.common.manager.jdhandler.UnionOpenPromotionBysubunionidGetHandler;
import unicorn.mp.common.manager.jdhandler.ZheTKUnionOpenPromotionBysubunionidGetHandler;
import unicorn.mp.common.manager.jdhandler.ZtkJingOrderQueryHandler;
import unicorn.mp.common.model.request.jd.*;
import unicorn.mp.common.model.response.PromotionAgg;
import unicorn.mp.common.model.response.jd.*;
import unicorn.mp.common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import unicorn.mp.common.model.request.jd.*;
import unicorn.mp.common.model.response.jd.*;

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
    @Resource
    private ZtkJingOrderQueryHandler ztkJingOrderQueryHandler;

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


    //传入商品详情页链接  获得聚合推广信息 zhetaoke(jing 转链api-)
    public PromotionCodeAggResp getPromoteAggByZhetaok(PromotionCodeReq req) {
        ZheTaoKePromotionCodeRequest request = new ZheTaoKePromotionCodeRequest();
        request.setApp_key("8bb75e5f298849a2901f19b2983708cc");
        request.setPromotionCodeReq(req);
        request.setSignurl("5");
        ZheTaoKeAggPromotionGetResponse result = zheTKUnionOpenPromotionBysubunionidGetHandler.sendRequestWrapper(request);
        if (result == null || result.getStatus() != 200) {
            log.error("result {}", JSON.toJSONString(result));
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

    //ztk订单查询
    public void getJingOrderQuery(long start, long end) {
        if (Long.compare((end-start), DateUtils.ONE_HOUR) >= 0) {
            throw new StandardException("查询间隔请小于一个小时...");
        }
        ZtkJingOrderQueryRequest request = new ZtkJingOrderQueryRequest();
        request.setStartTime(DateUtils.formatDateTime(start, DateUtils.YYYY_MM_DD_HH_MM_SS));
        request.setEndTime(DateUtils.formatDateTime(end, DateUtils.YYYY_MM_DD_HH_MM_SS));
        ZheTaoKeAggPromotionGetResponse response = ztkJingOrderQueryHandler.sendRequestWrapper(request);
        log.info("新订单查询：{}", JSON.toJSONString(response));
    }

}
