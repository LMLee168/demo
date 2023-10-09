package com.demo.api.model.request;

import com.demo.common.model.bo.BaseDO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityRequest extends ThirdRequest {

    /**
     * 文字、以英文逗号分隔的经度,纬度坐标（十进制，最多支持小数点后两位）、LocationID或Adcode（仅限中国城市）。例如 location=北京 或 location=116.41,39.92
     */
    private String location;
    private String adm;
    private String range;
    /**
     * 结果的数量，取值范围1-20，默认返回10个结果。
     */
    private Integer number = 10;
    /**
     * 简体中文	zh-hans、zh
     */
    private String lang = "zh";
}
