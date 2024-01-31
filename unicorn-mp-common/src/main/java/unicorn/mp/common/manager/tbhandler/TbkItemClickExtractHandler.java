package unicorn.mp.common.manager.tbhandler;

import com.alibaba.fastjson.JSON;
import unicorn.mp.common.manager.SavvyBuyConfigEnum;
import unicorn.mp.common.model.request.tb.TbkItemClickExtractRequest;
import unicorn.mp.common.model.response.tb.TbkItemClickExtractResponse;
import unicorn.mp.common.utils.TbSignUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 *  从淘宝客推广长链接或短链接中解析出open_iid(即识别商品的id)。
 */
@Slf4j
@Component
public class TbkItemClickExtractHandler extends AbstractTbSavvyBuyHandler<TbkItemClickExtractRequest, TbkItemClickExtractResponse> {
    @Override
    protected Map<String, Object> buildRequest(TbkItemClickExtractRequest request) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("method",getWorkWxApiEnum().getParamMethod() );
        params.put("click_url", request.getClick_url());
        TbSignUtil.getTbCommonItem(params);
        return params;
    }

    @Override
    protected TbkItemClickExtractResponse buildResponse(String jsonResult) {
        return JSON.parseObject(jsonResult, TbkItemClickExtractResponse.class);
    }

    @Override
    protected SavvyBuyConfigEnum getWorkWxApiEnum() {
        return SavvyBuyConfigEnum.ITEM_CLICK_EXTRACT;
    }
}
