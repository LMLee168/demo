package unicorn.mp.api.manager.weather.handler;

import com.alibaba.fastjson.JSON;
import unicorn.mp.api.manager.weather.config.ThirdApiConfigEnum;
import unicorn.mp.api.model.request.CityRequest;
import unicorn.mp.api.model.response.CityResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CitySearchHandler  extends AbstractThirdHandler<CityRequest, CityResponse> {

    private static final String softBreezeKey = "79dd926775494eacbdc30228f1718e98";

    @Override
    protected String urlSuffix() {
        return null;
    }

    @Override
    protected HttpMethod httpMethod() {
        return HttpMethod.GET;
    }

    @Override
    protected Map<String, Object> buildRequest(CityRequest request) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("location", request.getLocation());
        params.put("key", softBreezeKey);
        if (StringUtils.isNotBlank(request.getAdm())) {
            params.put("adm", request.getAdm());
        }
        if (StringUtils.isNotBlank(request.getRange())) {
            params.put("range", request.getRange());
        }
        params.put("number", request.getNumber());
        params.put("lang", request.getLang());
        return params;
    }

    @Override
    protected CityResponse buildResponse(String jsonResult) {
        return JSON.parseObject(jsonResult, CityResponse.class);
    }

    @Override
    protected String buildRequestUrl(CityRequest request) {
        return ThirdApiConfigEnum.CITY.getHost() + ThirdApiConfigEnum.CITY.getPath();
    }
}
