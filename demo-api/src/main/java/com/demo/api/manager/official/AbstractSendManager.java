package com.demo.api.manager.official;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public abstract class AbstractSendManager implements SendProvider {



    protected String getOfficialAccessToken(String accountNo){
//        return wxAccessTokenManager.getOfficialAccessToken(accountNo);
        return "";
    }

}
