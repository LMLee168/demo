package unicorn.mp.common.manager.tbhandler;

import com.alibaba.fastjson.JSON;
import unicorn.mp.common.manager.SavvyBuyConfigEnum;
import unicorn.mp.common.model.request.tb.TbkDgMaterialOptionalRequest;
import unicorn.mp.common.model.response.tb.TbkDgMaterialOptionalResponse;
import unicorn.mp.common.utils.TbSignUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 单个商品 q 和 adzone_id
 */
@Slf4j
@Component
public class TbkDgMaterialOptionalHandler extends AbstractTbSavvyBuyHandler<TbkDgMaterialOptionalRequest, TbkDgMaterialOptionalResponse> {
    @Override
    protected Map<String, Object> buildRequest(TbkDgMaterialOptionalRequest request) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("method", getWorkWxApiEnum().getParamMethod());
        params.put("adzone_id", request.getAdzone_id());
        params.put("promotion_type", request.getPromotionType());
        TbSignUtil.getTbCommonItem(params);
        return params;
    }

    @Override
    protected TbkDgMaterialOptionalResponse buildResponse(String jsonResult) {
        return JSON.parseObject(jsonResult, TbkDgMaterialOptionalResponse.class);
    }

    @Override
    protected SavvyBuyConfigEnum getWorkWxApiEnum() {
        return SavvyBuyConfigEnum.MATERIAL_OPTIONAL;
    }
}
