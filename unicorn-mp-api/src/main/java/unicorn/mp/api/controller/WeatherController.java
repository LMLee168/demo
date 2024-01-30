package unicorn.mp.api.controller;

import unicorn.mp.api.manager.WeatherManager;
import unicorn.mp.api.model.response.WeatherResponse;
import com.demo.common.encrypt.DataEncrypt;
import com.demo.common.enumation.ResponseUtil;
import com.demo.common.utils.ResultWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 天气查询
 */
@RestController
@RequestMapping("/demo/v1/weather")
public class WeatherController {

    @Resource
    private WeatherManager weatherManager;

    @GetMapping("/city")
    @DataEncrypt
    public ResultWrapper<?> weather(@RequestParam String city, @RequestParam(defaultValue = "3") Integer days) {
        WeatherResponse response = weatherManager.getWeather(city, days);
        return ResponseUtil.success(response);
    }

}
