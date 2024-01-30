package unicorn.mp.common.manager.tbhandler;

import com.alibaba.fastjson.JSON;
import unicorn.mp.common.manager.SavvyBuyConfigEnum;
import unicorn.mp.common.model.request.tb.TbkScPublisherInfoSaveRequest;
import unicorn.mp.common.model.response.tb.TbkScPublisherInfoSaveResponse;
import unicorn.mp.common.utils.TbSignUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 淘宝客-公用-私域用户备案  需授权
 * 通过入参渠道管理或会员运营管理的邀请码，生成渠道id或会员运营id，完成渠道或会员的备案。
 */
@Slf4j
@Component
public class TbkScPublisherInfoSaveHandler extends AbstractTbSavvyBuyHandler<TbkScPublisherInfoSaveRequest, TbkScPublisherInfoSaveResponse> {
    @Override
    protected Map<String, Object> buildRequest(TbkScPublisherInfoSaveRequest request) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("method", getWorkWxApiEnum().getParamMethod());
        params.put("ralation_from", request.getRalationFrom());
        params.put("offline_scene", request.getOfflineScene());
        params.put("online_scene", request.getOnlineScene());
        params.put("inviter_code", request.getInviterCode());
        params.put("note", request.getNote());
        params.put("register_info", request.getRegisterInfo());
        TbSignUtil.getTbCommonItem(params);
        return params;
    }

    @Override
    protected TbkScPublisherInfoSaveResponse buildResponse(String jsonResult) {
        return JSON.parseObject(jsonResult, TbkScPublisherInfoSaveResponse.class);
    }

    @Override
    protected SavvyBuyConfigEnum getWorkWxApiEnum() {
        return SavvyBuyConfigEnum.INFO_SAVE;
    }
}
