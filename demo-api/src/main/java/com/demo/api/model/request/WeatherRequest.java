package com.demo.api.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherRequest extends ThirdRequest {

    /**
     * 需要查询地区的LocationID或以英文逗号分隔的经度,纬度坐标
     */
    private String location;

    private Integer days = 3;

    private String lang = "zh";
    private String unit;
}
