package unicorn.mp.common.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import unicorn.mp.common.manager.pddhandler.PddSavvyBuyHandler;
import unicorn.mp.common.manager.pddhandler.ZhuanLianGetHander;
import unicorn.mp.common.model.request.jd.PromotionCodeReq;
import unicorn.mp.common.model.request.pdd.PddSavvyBuyRequest;
import unicorn.mp.common.model.response.jd.PromotionCodeAggResp;
import unicorn.mp.common.model.response.pdd.PddZhuanLianResponse;

import javax.annotation.Resource;

@Slf4j
@Component
public class CommonPddManager {

    @Resource
    private ZhuanLianGetHander zhuanLianGetHander;

    public PromotionCodeAggResp getPromoteAggByZhetaok(String content) {

        PddSavvyBuyRequest request = new PddSavvyBuyRequest();
        request.setContent(content);
        PddZhuanLianResponse response = zhuanLianGetHander.sendRequestWrapper(request);
        if(response.getGoods_zs_unit_generate_response()  == null) {

        }
        PromotionCodeAggResp resp = new PromotionCodeAggResp();

        return null;
    }

}
