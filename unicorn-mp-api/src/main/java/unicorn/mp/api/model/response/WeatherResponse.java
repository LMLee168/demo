package unicorn.mp.api.model.response;

import unicorn.mp.api.model.dto.ReferDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherResponse extends ThirdResponse {

    /**
     * 最近更新时间
     */
    private String updateTime;
    /**
     * 当前数据的响应式页面，便于嵌入网站或应用
     */
    private String fxLink;
    private List<DailyWeather> daily;

    private ReferDTO refer;

    @Getter
    @Setter
    public class DailyWeather{
        /**
         * 预报日期
         */
        private String fxDate;
        /**
         * 日出时间 高纬度可能为空
         */
        private String sunrise;
        /**
         * 日落时间
         */
        private String sunset;
        /**
         * 当天月落时间
         */
        private String moonset;
        /**
         * 当前月升时间
         */
        private String moonrise;
        /**
         * 月相名称
         */
        private String moonPhase;
        /**
         * 月相图标代码  https://dev.qweather.com/docs/resource/icons/ 下载
         */
        private String moonPhaseIcon;
        /**
         * 预报当天最高唯独
         */
        private String tempMax;
        /**
         * 最低温度
         */
        private String tempMin;
        /**
         * 预报白天天气状况的图标代码
         */
        private String iconDay;
        /**
         * 预报白天天气状况文字描述，包括阴晴雨雪等天气状态的描述
         */
        private String textDay;
        /**
         * 预报夜间天气状况的图标代码
         */
        private String iconNight;
        /**
         * 预报晚间天气状况文字描述，包括阴晴雨雪等天气状态的描述
         */
        private String textNight;
        /**
         * 预报白天风向360角度
         */
        private String wind360Day;
        /**
         * 预报白天风向
         */
        private String windDirDay;
        /**
         * 预报白天风力等级
         */
        private String windScaleDay;
        /**
         * 预报白天风速，公里/小时
         */
        private String windSpeedDay;
        /**
         * 预报夜间风向360角度
         */
        private String wind360Night;
        /**
         * 预报夜间当天风向
         */
        private String windNirNight;
        /**
         * 预报夜间风力等级
         */
        private String windScaleNight;
        /**
         * 预报夜间风速，公里/小时
         */
        private String windSpeedNight;
        /**
         * 相对湿度，百分比数值
         */
        private String humidity;
        /**
         * 预报当天总降水量，默认单位：毫米
         */
        private String precip;
        /**
         * 大气压强，默认单位：百帕
         */
        private String pressure;
        /**
         * 能见度，默认单位：公里
         */
        private String vis;
        /**
         * 云量，百分比数值。可能为空
         */
        private String cloud;
        /**
         * 紫外线强度指数
         */
        private String uvIndex;
    }
}
