package unicorn.mp.common.manager.tbhandler;

import com.alibaba.fastjson.JSON;
import unicorn.mp.common.manager.SavvyBuyConfigEnum;
import unicorn.mp.common.model.request.tb.TbkItemConvertRequest;
import unicorn.mp.common.model.response.tb.TbkItemConvertResponse;
import unicorn.mp.common.utils.TbSignUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 淘宝客商品链接转换
 */
@Slf4j
@Component
public class TbkItemConvertHandler extends AbstractTbSavvyBuyHandler<TbkItemConvertRequest, TbkItemConvertResponse> {
    @Override
    protected Map<String, Object> buildRequest(TbkItemConvertRequest request) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("method",getWorkWxApiEnum().getParamMethod() );
        params.put("adzone_id", request.getAdzone_id());
        params.put("fields", request.getFields());
        params.put("num_iids", request.getNum_iids());
        params.put("platform", request.getPlatform());
        params.put("unid", request.getUnid());
        params.put("dx", request.getDx());
        TbSignUtil.getTbCommonItem(params);
        return params;
    }

    @Override
    protected TbkItemConvertResponse buildResponse(String jsonResult) {
        return JSON.parseObject(jsonResult, TbkItemConvertResponse.class);
    }

    @Override
    protected SavvyBuyConfigEnum getWorkWxApiEnum() {
        return SavvyBuyConfigEnum.ITEM_CONVERT;
    }
}
