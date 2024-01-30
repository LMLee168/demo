package unicorn.mp.api.manager.weather.config;

import lombok.Getter;

@Getter
public enum ThirdApiConfigEnum {
    DAY("https://devapi.qweather.com", "/v7/weather/%sd"),
    DAY30( "https://devapi.qweather.com", "/v7/weather/30d"),
    CITY("https://geoapi.qweather.com", "/v2/city/lookup"),

    ;

    private String host;
    private String path;

    ThirdApiConfigEnum(String host, String path) {
        this.host = host;
        this.path = path;
    }
}
