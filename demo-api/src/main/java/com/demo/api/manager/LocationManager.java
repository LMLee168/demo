package com.demo.api.manager;

import com.alibaba.fastjson.JSON;
import com.demo.api.exception.ResponseError;
import com.demo.api.manager.weather.handler.CitySearchHandler;
import com.demo.api.model.request.CityRequest;
import com.demo.api.model.response.CityResponse;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class LocationManager {

    @Resource
    private CitySearchHandler citySearchHandler;

    /**
     *
     * @param locationId 城市名称/经纬度
     * @param adm  当前搜索城市的上级行政区划
     * @return
     */
    public CityResponse citySearch(String locationId, String adm) {
        CityRequest request = new CityRequest();
        request.setLocation(locationId);
        request.setAdm(adm);
        CityResponse city = citySearchHandler.sendRequest(request);
        if ( city == null) {
            throw new RuntimeException(ResponseError.SYSTEM_ERROR.getToast());
        }
        if (!city.getCode().equals("200")) {
            ResponseError error = ResponseError.getByCode(Integer.valueOf(city.getCode()));
            throw new RuntimeException(error.getToast());
        }
        System.out.println(JSON.toJSONString(city));
        return city;
    }


}
