package com.demo.api.manager.savvybuy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("pdd")
public class SavvyBuyPdd implements SavvyBuyProvider{
    @Override
    public String queryCoupon(String key) {
        return null;
    }
}
