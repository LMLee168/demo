package unicorn.mp.api.manager.official;

import unicorn.mp.common.manager.TokenManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public abstract class AbstractSendManager implements SendProvider {

    @Resource
    private TokenManager tokenManager;


    protected String getOfficialAccessToken(String accountNo){
        tokenManager.getOfficialAccessToken(accountNo);
        return "";
    }

}
