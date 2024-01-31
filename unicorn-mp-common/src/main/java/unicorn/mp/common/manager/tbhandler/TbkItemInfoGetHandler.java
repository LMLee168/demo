package unicorn.mp.common.manager.tbhandler;

import com.alibaba.fastjson.JSON;
import unicorn.mp.common.manager.SavvyBuyConfigEnum;
import unicorn.mp.common.model.request.tb.TbkItemInfoGetRequest;
import unicorn.mp.common.model.response.tb.TbkItemInfoGetResponse;
import unicorn.mp.common.utils.TbSignUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 淘宝客商品详情查询（简版）
 */
@Slf4j
@Component
public class TbkItemInfoGetHandler extends AbstractTbSavvyBuyHandler<TbkItemInfoGetRequest, TbkItemInfoGetResponse> {
    @Override
    protected Map<String, Object> buildRequest(TbkItemInfoGetRequest request) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("method", getWorkWxApiEnum().getParamMethod());
        params.put("num_iids", request.getNum_iids());
        params.put("platform", request.getPlatform());
        params.put("ip", request.getIp());
        params.put("biz_scene_id", request.getBiz_scene_id());
        params.put("promotion_type", request.getPromotion_type());
        params.put("relation_id", request.getRelation_id());
        params.put("manage_item_pub_id", request.getManage_item_pub_id());
        TbSignUtil.getTbCommonItem(params);
        return params;
    }

    @Override
    protected TbkItemInfoGetResponse buildResponse(String jsonResult) {
        return JSON.parseObject(jsonResult,TbkItemInfoGetResponse.class );
    }

    @Override
    protected SavvyBuyConfigEnum getWorkWxApiEnum() {
        return SavvyBuyConfigEnum.ITEM_INFO_GET;
    }
}
