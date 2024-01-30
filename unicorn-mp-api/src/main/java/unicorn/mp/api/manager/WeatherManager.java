package unicorn.mp.api.manager;

import com.alibaba.fastjson.JSON;
import unicorn.mp.api.exception.ResponseError;
import unicorn.mp.api.manager.weather.handler.WeatherHandler;
import unicorn.mp.api.model.response.WeatherResponse;
import unicorn.mp.api.model.request.WeatherRequest;
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
