package unicorn.mp.common.manager.tbhandler;

import com.alibaba.fastjson.JSON;
import unicorn.mp.common.manager.SavvyBuyConfigEnum;
import unicorn.mp.common.model.request.tb.TbkScPublisherInfoGetRequest;
import unicorn.mp.common.model.response.tb.TbkScPublisherInfoGetResponse;
import unicorn.mp.common.utils.TbSignUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 淘宝客-公用-私域用户备案信息查询 需授权
 * 查询已生成的渠道id或会员运营id的相关信息。
 */
@Slf4j
@Component
public class TbkScPublisherInfoGetHandler extends AbstractTbSavvyBuyHandler<TbkScPublisherInfoGetRequest, TbkScPublisherInfoGetResponse> {
    @Override
    protected Map<String, Object> buildRequest(TbkScPublisherInfoGetRequest request) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("method", getWorkWxApiEnum().getParamMethod());
        params.put("relation_id", request.getRelationId() != null ? request.getRelationId().toString() : "");
        params.put("page_no", request.getPageNo().toString());
        params.put("page_size", request.getPageSize().toString());
        params.put("info_type", request.getInfoType().toString());
        params.put("relation_app", request.getRelationApp());
        params.put("special_id", request.getSpecialId());
        params.put("external_id", request.getSpecialId());
        TbSignUtil.getTbCommonItem(params);
        return params;
    }

    @Override
    protected TbkScPublisherInfoGetResponse buildResponse(String jsonResult) {
        return JSON.parseObject(jsonResult, TbkScPublisherInfoGetResponse.class);
    }

    @Override
    protected SavvyBuyConfigEnum getWorkWxApiEnum() {
        return SavvyBuyConfigEnum.PUBLISHER_INFO_GET;
    }
}
