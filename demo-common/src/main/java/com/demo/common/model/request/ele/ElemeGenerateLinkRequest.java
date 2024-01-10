package com.demo.common.model.request.ele;

import com.demo.common.model.bo.BaseDO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ElemeGenerateLinkRequest extends BaseDO {
    //ztk对接密钥appkey
    private String appKey;
    //对应的淘客账号授权SID
    private String sid;
    /**
     * 活动ID：10144，饿了么天天领红包，最高抢66元大额红包，预估佣金收益6%。（重点推广！重点推广！重点推广！）
     * 活动ID：10690，赏金红包，打开饿了么APP 扫红包。（重点推广！重点推广！重点推广！）
     * 活动ID：10423，周五5折天，5折红包限时限量抢,预估佣金收益6%。
     * 活动ID：10247，零售会场（新），超低价爆品来袭，预估佣金收益4%。
     * 活动ID：10622，品牌馆，超多品牌限时活动，预估佣金收益6%。
     * 活动ID：10725，星座鲜花全国活动，满79减20元，满159减40元，预估佣金收益4%。
     * 更多饿了么活动，请参考接口：http://api.zhetaoke.com:10000/api/api_activity.ashx?appkey=&activityId=&type=11
     * 更多饿了么活动，请参考接口：http://api.zhetaoke.com:10000/api/api_activity.ashx?appkey=&activityId=&type=11
     * 更多饿了么活动，请参考接口：http://api.zhetaoke.com:10000/api/api_activity.ashx?appkey=&activityId=&type=11
     */
    private String activityId;
    //自定义参数;只允许数字，最大长度11位。
    //此字段可以用来区分用户，进行下级返利。
    private String customerId;
}
