package com.demo;

import com.alibaba.fastjson.JSON;
import unicorn.mp.api.manager.LocationManager;
import unicorn.mp.api.manager.WeatherManager;
import unicorn.mp.api.model.response.CityResponse;
import org.junit.Test;

import javax.annotation.Resource;

public class WeatherTest extends BaseTest{

    @Resource
    private WeatherManager weatherManager;

    @Test
    public void weather(){
        weatherManager.getWeather("101210106", 3);
    }

    @Resource
    private LocationManager locationManager;

    @Test
    public void city() {
        CityResponse response = locationManager.citySearch("日照", "日照");
        if (response == null) {
            return;
        }
        CityResponse.Location location = response.getLocation().stream()
                .filter(item -> "日照".equals(item.getName())).findFirst().orElse(null);
        System.out.println(JSON.toJSONString(location));
        if (location == null) {
            return;
        }
//        weatherManager.getWeather(location.getId()+"", 3);
    }
}
