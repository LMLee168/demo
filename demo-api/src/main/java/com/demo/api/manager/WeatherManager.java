package com.demo.api.manager;

import com.alibaba.fastjson.JSON;
import com.demo.api.exception.ResponseError;
import com.demo.api.manager.weather.handler.WeatherHandler;
import com.demo.api.model.response.WeatherResponse;
import com.demo.api.model.request.WeatherRequest;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 天气
 */
@Component
public class WeatherManager {

    @Resource
    private WeatherHandler weatherHandler;

    public WeatherResponse getWeather(String location, Integer days) {
        WeatherRequest request = new WeatherRequest();
        request.setLocation(location);
        request.setDays(days);
        WeatherResponse softBreeze = weatherHandler.sendRequest(request);
        if ( softBreeze == null) throw new RuntimeException(ResponseError.SYSTEM_ERROR.getToast());
        if (!softBreeze.getCode().equals("200")) {
            ResponseError error = ResponseError.getByCode(Integer.valueOf(softBreeze.getCode()));
            throw new RuntimeException(error.getToast());
        }
        System.out.println(JSON.toJSONString(softBreeze));
        return softBreeze;
    }



}
