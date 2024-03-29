package unicorn.mp.api.model.response;

import unicorn.mp.api.model.dto.ReferDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CityResponse extends ThirdResponse {

    private List<Location> location;

    private ReferDTO refer;

    @Getter
    @Setter
    public class Location {
        /**
         * 地区/城市名称
         */
        private String name;
        /**
         * 地区/城市ID
         */
        private Long id;
        /**
         * 地区/城市纬度
         */
        private String lat;
        /**
         * 地区/城市经度
         */
        private String lon;
        /**
         * 地区/城市的上级行政区划名称
         */
        private String adm2;
        /**
         * 地区/城市所属一级行政区域
         */
        private String adm1;
        /**
         * 地区/城市所属国家名称
         */
        private String country;
        /**
         * 地区/城市所在时区
         */
        private String tz;
        /**
         * 地区/城市目前与UTC时间偏移的小时数 https://dev.qweather.com/docs/resource/glossary/#utc-offset
         */
        private String utcOffset;
        /**
         * 地区/城市是否当前处于夏令时。1 表示当前处于夏令时，0 表示当前不是夏令时。
         */
        private String isDst;
        /**
         * 地区/城市的属性
         */
        private String type;
        /**
         * 地区评分  https://dev.qweather.com/docs/resource/glossary/#rank
         */
        private String rank;
        /**
         * 该地区的天气预报网页链接，便于嵌入你的网站或应用
         */
        private String fxLink;
    }

}
