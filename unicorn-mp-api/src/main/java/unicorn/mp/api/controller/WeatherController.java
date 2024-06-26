package unicorn.mp.api.controller;

import unicorn.mp.api.manager.WeatherManager;
import unicorn.mp.api.model.response.WeatherResponse;
import unicorn.mp.common.encrypt.DataEncrypt;
import unicorn.mp.common.enumation.ResponseUtil;
import unicorn.mp.common.utils.ResultWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 天气查询
 */
@RestController
@RequestMapping("/unicorn/v1/weather")
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
