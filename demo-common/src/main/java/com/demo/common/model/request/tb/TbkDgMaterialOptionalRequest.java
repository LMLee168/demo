package com.demo.common.model.request.tb;

import com.demo.common.model.request.SavvyBuyRequest;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class TbkDgMaterialOptionalRequest extends SavvyBuyRequest {
    /**
     * 商品筛选(特定媒体支持)-店铺dsr评分。筛选大于等于当前设置的店铺dsr评分的商品0-50000之间
     */
    private Integer startDsr;
    private Integer pageNo=1;
    private Integer pageSize = 20;
    /**
     * 链接形式：1：PC，2：无线，默认：１
     */
    private Integer platform = 1;
    /**
     * 商品筛选-淘客佣金比率上限。如：1234表示12.34%
     */
    private Integer endTkRate;
    /**
     * 商品筛选-淘客佣金比率下限。如：1234表示12.34%
     */
    private Integer startTkRate;
    /**
     * 商品筛选-折扣价范围上限。单位：元
     */
    private Integer endPrice;
    private Integer startPrice;
    /**
     * 商品筛选-是否海外商品。true表示属于海外商品，false或不设置表示不限
     */
    private Boolean isOverseas;
    /**
     * 商品筛选-是否天猫商品。true表示属于天猫商品，false或不设置表示不限
     */
    private Boolean isTmall;
    /**
     * 排序_des（降序），排序_asc（升序），销量（total_sales），淘客佣金比率（tk_rate）， 累计推广量（tk_total_sales），总支出佣金（tk_total_commi），价格（price），匹配分（match）
     */
    private String sort;
    /**
     * 商品筛选-所在地
     */
    private String itemloc;
    /**
     * 商品筛选-后台类目ID。用,分割，最大10个，该ID可以通过taobao.itemcats.get接口获取到
     */
    private String cat;
    /**
     * 商品筛选-查询词
     */
    private String q;
    /**
     * 不传时默认物料id=2836；如果直接对消费者投放，可使用官方个性化算法优化的搜索物料id=17004
     */
    private Integer materialId;
    /**
     * 优惠券筛选-是否有优惠券。true表示该商品有优惠券，false或不设置表示不限
     */
    private Boolean hasCoupon;
    /**
     * ip参数影响邮费获取，如果不传或者传入不准确，邮费无法精准提供
     */
    private String ip;
    /**
     * mm_xxx_xxx_12345678三段式的最后一段数字
     */
    @NonNull
    public Long adzone_id = 111482500415L;
    /**
     * 商品筛选-是否包邮。true表示包邮，false或不设置表示不限
     */
    private Boolean needFreeShipment;
    /**
     * 商品筛选-是否加入消费者保障。true表示加入，false或不设置表示不限
     */
    private Boolean needPrepay;
    /**
     * 商品筛选(特定媒体支持)-成交转化是否高于行业均值。True表示大于等于，false或不设置表示不限
     */
    private Boolean includePayRate30;
    /**
     * 商品筛选-好评率是否高于行业均值。True表示大于等于，false或不设置表示不限
     */
    private Boolean includeGoodRate;
    /**
     * 商品筛选(特定媒体支持)-退款率是否低于行业均值。True表示大于等于，false或不设置表示不限
     */
    private Boolean includeRfdRate;
    /**
     * 商品筛选-牛皮癣程度。取值：1不限，2无，3轻微
     */
    private Integer npxLevel;
    /**
     * 智能匹配-设备号加密类型：MD5
     */
    private String deviceEncrypt;
    /**
     * 智能匹配-设备号加密后的值（MD5加密需32位小写）
     */
    private String deviceValue;
    /**
     * 智能匹配-设备号类型：IMEI，或者IDFA，或者UTDID（UTDID不支持MD5加密），或者OAID
     */
    private String deviceType;
    /**
     * 商品筛选-KA媒体淘客佣金比率上限。如：1234表示12.34%
     */
    private Integer endKaTkRate;
    /**
     * 商品筛选-KA媒体淘客佣金比率下限。如：1234表示12.34%
     */
    private Integer startKaTkRate;
    /**
     * 锁佣结束时间
     */
    private Long lockRateEndTime;
    private Long lockRateStartTime;
    /**
     * 本地化业务入参-LBS信息-经度
     */
    private String longitude;
    private String latitude;
    /**
     * 本地化业务入参-LBS信息-国标城市码，仅支持单个请求，请求饿了么卡券物料时，该字段必填。 （详细城市ID见：https://mo.m.taobao.com/page_2020010315120200508）
     */
    private String cityCode;
    /**
     * 商家id，仅支持饿了么卡券商家ID，支持批量请求1-100以内，多个商家ID使用英文逗号分隔
     */
    private String sellerIds;
    /**
     * 会员运营ID
     */
    private String specialId;
    /**
     * 渠道关系ID，仅适用于渠道推广场景
     */
    private String relationId;
    /**
     * 本地化业务入参-分页唯一标识，非首页的请求必传，值为上一页返回结果中的page_result_key字段值
     */
    private String pageResultKey;
    /**
     * 人群ID，仅适用于物料评估场景material_id=41377
     */
    private Long ucrowdId;
    /**
     * 物料评估-商品列表
     */
    private List<Ucrowdrankitems> ucrowdRankItems;
    /**
     * 是否获取前N件佣金信息 0否，1是，其他值否
     */
    private Integer getTopnRate;
    /**
     * 1-动态ID转链场景，2-消费者比价场景（不填默认为1）
     */
    private String bizSceneId = "1";
    /**
     * 1-自购省，2-推广赚（代理模式专属ID，代理模式必填，非代理模式不用填写该字段）
     */
    private String promotionType = "2";


    @Getter
    @Setter
    public class Ucrowdrankitems{
        /**
         * 物料评估-商品佣金率，如：1234表示12.34%，material_id=41377时选填
         */
        private Integer commirate;
        /**
         * 物料评估-商品价格，单位：元，material_id=41377时选填
         */
        private BigDecimal price;
        /**
         * 物料评估-商品ID，material_id=41377时必填
         */
        private String itemId;
    }






}
