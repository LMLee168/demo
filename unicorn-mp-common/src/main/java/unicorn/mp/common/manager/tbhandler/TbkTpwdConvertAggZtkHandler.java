package unicorn.mp.common.manager.tbhandler;

import com.alibaba.fastjson.JSON;
import unicorn.mp.common.manager.SavvyBuyConfigEnum;
import unicorn.mp.common.model.request.tb.TbkTpwdConvertAggZtkRequest;
import unicorn.mp.common.model.response.tb.ZheTaoKeTbAggPromotionGetResponse;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 淘口令解析-得商品id
 */
@Slf4j
@Component
public class TbkTpwdConvertAggZtkHandler extends AbstractTbSavvyBuyHandler<TbkTpwdConvertAggZtkRequest, ZheTaoKeTbAggPromotionGetResponse> {
    @Override
    protected Map<String, Object> buildRequest(TbkTpwdConvertAggZtkRequest request) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("appKey", "8bb75e5f298849a2901f19b2983708cc");
        params.put("pid", request.getPid());
        params.put("sid", request.getSid());
        params.put("tkl", request.getTkl());
        params.put("relation_id",request.getRelationId());
        params.put("special_id", request.getSpecialId());
        params.put("signurl", request.getSignUrl());
        return params;
    }

    @Override
    protected ZheTaoKeTbAggPromotionGetResponse buildResponse(String jsonResult) {
        ZheTaoKeTbAggPromotionGetResponse response = JSON.parseObject(jsonResult, ZheTaoKeTbAggPromotionGetResponse.class);
        response.setCode(response.getStatus().toString());
        return response;
    }

    @Override
    protected SavvyBuyConfigEnum getWorkWxApiEnum() {
        return SavvyBuyConfigEnum.AGG_ZTK_TPWD_CONVERT;
    }
}
