package com.demo.api.manager.weather.handler;

import com.alibaba.fastjson.JSON;
import com.demo.api.manager.weather.config.ThirdApiConfigEnum;
import com.demo.api.model.response.WeatherResponse;
import com.demo.api.model.request.WeatherRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class WeatherHandler extends AbstractThirdHandler<WeatherRequest, WeatherResponse> {

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
    protected Map<String, Object> buildRequest(WeatherRequest request) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("location", request.getLocation());
        params.put("key", softBreezeKey);
        params.put("lang", request.getLang());
        if (StringUtils.isNotBlank(request.getUnit())) {
            params.put("unit", request.getUnit());
        }
        return params;
    }

    @Override
    protected WeatherResponse buildResponse(String jsonResult) {
        return JSON.parseObject(jsonResult, WeatherResponse.class);
    }

    @Override
    protected String buildRequestUrl(WeatherRequest request) {
        String url = ThirdApiConfigEnum.DAY.getHost() + ThirdApiConfigEnum.DAY.getPath();
        return String.format(url, request.getDays());
    }


}
