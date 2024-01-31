package unicorn.mp.common.manager;

import com.alibaba.fastjson.JSONObject;
import unicorn.mp.common.cache.CacheKeys;
import unicorn.mp.common.cache.CacheTemplate;
import unicorn.mp.common.exception.StandardException;
import unicorn.mp.common.model.response.WxTokenResponse;
import unicorn.mp.common.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class TokenManager {

    @Resource
    private CacheTemplate cacheTemplate;
    private static final String testOfficialAppId= "wx3adc667bcf3c65a6";
    private static final String testOfficialAppSecret="9d117d215a9ec48896d6b996637925f3";
    private static final String ACCESSTOKEN_GRANT_TYPE_AUTH_CODE = "client_credential";
    private static final String ACCESSTOKEN_BASE_URL = "https://api.weixin.qq.com/cgi-bin/token";



    public String getOfficialAccessToken(String accountNo) {
        return getOfficialAccessInfo(false, accountNo).getAccessToken();
    }

    private WxTokenResponse getOfficialAccessInfo(boolean forceRefresh, String accountNo) {
        String officialKey = CacheKeys.OFFICIAL_ACCESSTOKEN_KEY.getKey(accountNo);
        if (!forceRefresh && cacheTemplate.hasKey(officialKey)) {
            String accessToken = cacheTemplate.getValue(officialKey);
            Long expire = cacheTemplate.getExpire(officialKey, TimeUnit.SECONDS);
            WxTokenResponse wxTokenInfo = new WxTokenResponse();
            wxTokenInfo.setAccessToken(accessToken);
            wxTokenInfo.setExpiresIn(expire.intValue());
            return wxTokenInfo;
        }
        Map<String, Object> params = new HashMap<>();
        params.put("appid", testOfficialAppId);
        params.put("secret", testOfficialAppSecret);
        params.put("grant_type", ACCESSTOKEN_GRANT_TYPE_AUTH_CODE);
        String result = null;
        try {
            result = HttpUtil.doGet(ACCESSTOKEN_BASE_URL, params);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        if (StringUtils.isEmpty(result)) {
            log.error("调用微信accessToken接口失败, 返回结果为空");
            throw new StandardException("10000", "调用微信accessToken接口失败, 返回结果为空");
        }
        WxTokenResponse wxTokenInfo = JSONObject.parseObject(result, WxTokenResponse.class);
        if (wxTokenInfo.getErrmsg() != null && wxTokenInfo.getErrcode() != 0) {
            log.error("调用微信accessToken接口失败, errcode = {}, errmsg = {}", wxTokenInfo.getErrcode(), wxTokenInfo.getErrmsg());
            throw new StandardException("10000", wxTokenInfo.getErrmsg());
        }
        cacheTemplate.setValue(officialKey, wxTokenInfo.getAccessToken(), wxTokenInfo.getExpiresIn() - 10, TimeUnit.SECONDS);
        return wxTokenInfo;
    }
}
