package unicorn.mp.common.manager.pddhandler;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;
import unicorn.mp.common.manager.SavvyBuyConfigEnum;
import unicorn.mp.common.model.request.pdd.PddSavvyBuyRequest;
import unicorn.mp.common.model.response.pdd.PddResultResponse;
import unicorn.mp.common.model.response.pdd.PddZhuanLianResponse;

import java.util.Map;

@Component
public class ZhuanLianGetHander extends AbstractPddSavvyBuyHandler<PddSavvyBuyRequest, PddZhuanLianResponse>{
    @Override
    protected Map<String, Object> buildRequest(PddSavvyBuyRequest request) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("appkey", request.getApp_key());
        params.put("content", request.getContent());
        params.put("pdd_app_key", request.getPddAppKey());
        params.put("pdd_app_secret", request.getPddAppSecret());
        params.put("pid", request.getPid());
        return params;
    }

    @Override
    protected PddZhuanLianResponse buildResponse(String jsonResult) {
        return JSON.parseObject(jsonResult, PddZhuanLianResponse.class);
    }

    @Override
    protected SavvyBuyConfigEnum getWorkWxApiEnum() {
        return SavvyBuyConfigEnum.ZTK_PDD_ZHUANLIAN_NEW;
    }
}
