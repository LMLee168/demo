package unicorn.mp.api.manager.savvybuy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import unicorn.mp.common.manager.CommonPddManager;

import javax.annotation.Resource;

@Slf4j
@Component("pdd")
public class SavvyBuyPdd implements SavvyBuyProvider{

    @Resource
    private CommonPddManager commonPddManager;

    @Override
    public String queryCoupon(String key) {
        commonPddManager.getPromoteAggByZhetaok(key);
        return null;
    }
}
