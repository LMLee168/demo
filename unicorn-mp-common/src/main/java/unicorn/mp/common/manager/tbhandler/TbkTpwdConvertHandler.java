package unicorn.mp.common.manager.tbhandler;

import com.alibaba.fastjson.JSON;
import unicorn.mp.common.manager.SavvyBuyConfigEnum;
import unicorn.mp.common.model.request.tb.TbkTpwdConvertRequest;
import unicorn.mp.common.model.response.tb.TbkTpwdConvertResponse;
import unicorn.mp.common.utils.TbSignUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 淘口令解析-得商品id
 */
@Slf4j
@Component
public class TbkTpwdConvertHandler extends AbstractTbSavvyBuyHandler<TbkTpwdConvertRequest, TbkTpwdConvertResponse> {
    @Override
    protected Map<String, Object> buildRequest(TbkTpwdConvertRequest request) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("method", getWorkWxApiEnum().getParamMethod());
        params.put("password_content", request.getPwd_content());
        params.put("adzone_id", request.getAdzone_id());
        TbSignUtil.getTbCommonItem(params);
        return params;
    }

    @Override
    protected TbkTpwdConvertResponse buildResponse(String jsonResult) {
        return JSON.parseObject(jsonResult, TbkTpwdConvertResponse.class);
    }

    @Override
    protected SavvyBuyConfigEnum getWorkWxApiEnum() {
        return SavvyBuyConfigEnum.TPWD_CONVERT;
    }
}
