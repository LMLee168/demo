package unicorn.mp.common.model.request.jd;

import unicorn.mp.common.model.request.SavvyBuyRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ZtkJingOrderQueryRequest extends SavvyBuyRequest {

    //京东开放平台应用的appkey，注意此参数值如果为空，下面的key参数必须传入。
    private String jd_app_key;
    //京东开放平台应用的appsecret，注意此参数值如果为空，下面的key参数必须传入。
    private String jd_app_secret;
    //页码
    private String pageIndex = "1";
    //数量
    private String pageSize = "20";
    //订单时间查询类型(1：下单时间，2：完成时间（购买用户确认收货时间），3：更新时间
    private String type = "1";
    //开始时间 格式yyyy-MM-dd HH:mm:ss，与endTime间隔不超过1小时
    private String startTime;
    //结束时间 格式yyyy-MM-dd HH:mm:ss，与startTime间隔不超过1小时
    private String endTime;
    //子推客unionID，传入该值可查询子推客的订单，注意不可和key同时传入。
    private String childUnionId;
    //工具商传入推客的授权key，可帮助该推客查询订单，注意不可和childUnionid同时传入。注意此参数值如果为空，jd_app_key和jd_app_secret参数必须传入。
    private String key;
    //支持出参数据筛选，逗号','分隔，目前可用：goodsInfo（商品信息）,categoryInfo(类目信息）
    private String fields;
}
