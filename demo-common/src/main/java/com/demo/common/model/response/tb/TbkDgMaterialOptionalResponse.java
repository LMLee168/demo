package com.demo.common.model.response.tb;
import com.alibaba.fastjson.annotation.JSONField;
import com.demo.common.model.response.TbResultResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TbkDgMaterialOptionalResponse extends TbResultResponse {
    @JSONField(name = "total_results")
    private Integer totalResults;
    /**
     * 本地化-lbs分页标识，请在下一次翻页时作为入参传入
     */
    @JSONField(name = "page_result_key")
    private String pageResultKey;

    @JSONField(name = "result_list")
    private List<MapData> resultList;

    @Getter
    @Setter
    public class MapData{
        /**
         * 优惠券信息-优惠券开始时间 2017-10-29
         */
        @JSONField(name = "coupon_start_time")
        private String couponStartTime;
        @JSONField(name = "coupon_end_time")
        private String couponEndTime;
        /**
         * 商品信息-定向计划信息 {"19013551":"2850","74510538":"2550"}
         */
        @JSONField(name = "info_dxjh")
        private String infoDxjh;
        /**
         * 商品信息-淘客30天推广量
         */
        @JSONField(name = "tk_total_sales")
        private String tkTotalSales;
        /**
         * 商品信息-月支出佣金(该字段废弃，请勿再用)
         */
        @JSONField(name = "tk_total_commi")
        private String tkTotalCommi;
        /**
         * 优惠券信息-优惠券id  d62db1ab8d9546b1bf0ff49bda5fc33b
         */
        private String couponId;
        /**
         * 商品信息-宝贝id(该字段废弃，请勿再用)
         */
        @JSONField(name = "num_iid")
        private String numIid;
        /**
         * 商品信息-商品标题:毛呢阔腿裤港味复古女裤子秋冬九分裤萝卜裤显瘦高腰韩版2017新款
         */
        private String title;
        /**
         * 商品信息-商品主图
         */
        @JSONField(name = "pict_url")
        private String pictUrl;
        /**
         * 商品信息-商品小图列表
         */
        @JSONField(name = "small_images")
        private List<String> smallImages;
        /**
         * 商品信息-商品一口价格
         */
        @JSONField(name = "reserve_price")
        private String reservePrice;
        /**
         * 折扣价（元） 若属于预售商品，付定金时间内，折扣价=预售价
         */
        @JSONField(name = "zk_final_price")
        private String zkFinalPrice;
        /**
         * 店铺信息-卖家类型。0表示集市，1表示天猫
         */
        @JSONField(name = "user_type")
        private Integer userType;
        /**
         * 商品信息-宝贝所在地
         */
        private String provcity;
        /**
         * 链接-宝贝地址
         */
        @JSONField(name = "item_url")
        private String itemUrl;
        /**
         * 商品信息-是否包含营销计划
         */
        @JSONField(name = "include_mkt")
        private String includeMkt;
        /**
         * 商品信息-是否包含定向计划
         */
        @JSONField(name = "include_dxjh")
        private String includeDxjh;
        /**
         * 商品信息-佣金比率。1550表示15.5% 反佣
         */
        @JSONField(name = "commission_rate")
        private String commissionRate;
        /**
         * 商品信息-30天销量（饿了么卡券信息-总销量）
         */
        private Integer volume;
        /**
         * 店铺信息-卖家id
         */
        @JSONField(name = "seller_id")
        private Long sellerId;
        /**
         * 店铺信息-店铺名称
         */
        @JSONField(name = "shop_title")
        private String shopTitle;
        /**
         * 优惠券信息-优惠券总量
         */
        @JSONField(name = "coupon_total_count")
        private Integer couponTotalCount;
        /**
         * 优惠券信息-优惠券剩余量
         */
        @JSONField(name = "coupon_remain_count")
        private Integer couponRemainCount;
        /**
         * 优惠券信息-优惠券满减信息: 满299元减20元
         */
        @JSONField(name = "coupon_info")
        private String couponInfo;
        /**
         * 商品信息-佣金类型。MKT表示营销计划，SP表示定向计划，COMMON表示通用计划
         */
        @JSONField(name = "commission_type")
        private String commisssionType;
        /**
         * 店铺信息-店铺dsr评分
         */
        @JSONField(name = "shop_dsr")
        private Integer shopDsr;
        /**
         * 链接-宝贝+券二合一页面链接
         */
        @JSONField(name = "coupon_share_url")
        private String couponShareUrl;
        /**
         * 链接-宝贝推广链接
         */
        private String url;
        /**
         * 商品信息-一级类目名称
         */
        @JSONField(name = "level_one_category_name")
        private String levelOneCategoryName;
        /**
         * 商品信息-一级类目ID
         */
        @JSONField(name = "level_one_category_id")
        private Long levelOneCategoryId;
        /**
         * 商品信息-叶子类目名称
         */
        @JSONField(name = "category_name")
        private String categoryName;
        /**
         * 商品信息-叶子类目id
         */
        @JSONField(name = "category_id")
        private Long categoryId;
        /**
         * 商品信息-商品短标题
         */
        @JSONField(name = "short_title")
        private String shortTitle;
        /**
         * 商品信息-商品白底图
         */
        @JSONField(name = "white_image")
        private String whiteImage;
        /**
         * 拼团专用-拼团结束时间 2018-08-21 11:23:43
         */
        private String oetime;
        private String estime;
        /**
         * 拼团专用-拼团几人团
         */
        @JSONField(name = "jdd_num")
        private Integer jddNum;
        /**
         * 拼团专用-拼团拼成价，单位元
         */
        @JSONField(name = "jdd_price")
        private String jddPrice;
        /**
         * 预售专用-预售数量
         */
        @JSONField(name = "uv_sum_pre_sale")
        private Integer uvSumPreSale;
        /**
         * 链接-物料块id(测试中请勿使用)
         */
        @JSONField(name = "x_id")
        private String xId;
        /**
         * 优惠券信息-优惠券起用门槛，满X元可用。如：满299元减20元
         */
        @JSONField(name = "coupon_start_fee")
        private String couponStartFee;
        /**
         * 优惠券（元） 若属于预售商品，该优惠券付尾款可用，付定金不可用
         */
        @JSONField(name = "coupon_amount")
        private String couponAmount;
        /**
         * 商品信息-宝贝描述(推荐理由)
         */
        @JSONField(name = "item_description")
        private String itemDescription;
        /**
         * 店铺信息-卖家昵称
         */
        private String nick;
        /**
         * 拼团专用-拼团一人价（原价)，单位元
         */
        @JSONField(name = "orig_price")
        private String origPrice;
        /**
         * 拼团专用-拼团库存数量
         */
        @JSONField(name = "total_stock")
        private Integer totalStock;
        /**
         * 拼团专用-拼团已售数量
         */
        @JSONField(name = "sell_num")
        private Integer sellNum;
        /**
         * 拼团专用-拼团剩余库存
         */
        private Integer stock;
        /**
         * 营销-天猫营销玩法 前n件x折
         */
        @JSONField(name = "tmall_play_activity_info")
        private String tmallPlayActivityInfo;
        /**
         * 商品信息-宝贝id
         */
        @JSONField(name = "item_id")
        private String itemId;
        /**
         * 商品邮费
         */
        @JSONField(name = "real_post_fee")
        private String realPostFee;
        /**
         * 锁住的佣金率
         */
        @JSONField(name = "lock_rate")
        private String lockRate;
        /**
         * 锁佣结束时间
         */
        @JSONField(name = "lock_rate_end_time")
        private Long lockRateEndTime;
        @JSONField(name = "lock_rate_start_time")
        private Long lockRateStartTime;
        /**
         * 预售商品-优惠 付定金立减5元
         */
        @JSONField(name = "presale_discount_fee_text")
        private String presaleDiscountFeeText;
        /**
         * 预售商品-付尾款结束时间（毫秒）
         */
        @JSONField(name = "presale_tail_end_time")
        private Long presaleTailEndTime;
        @JSONField(name = "presale_tail_start_time")
        private Long presaleTailStartTime;
        /**
         * 预售商品-付定金结束时间（毫秒）
         */
        @JSONField(name = "presale_end_time")
        private Long presaleEndTime;
        @JSONField(name = "presale_start_time")
        private Long presaleStartTime;
        /**
         * 预售商品-定金（元）
         */
        @JSONField(name = "presale_deposit")
        private String presaleDeposit;
        /**
         * 预售有礼-淘礼金发放时间
         */
        @JSONField(name = "ysyl_tlj_send_time")
        private String ysylTljSendTime;
        /**
         * 预售有礼-佣金比例（ 预售有礼活动享受的推广佣金比例，注：推广该活动有特殊分成规则，请详见：https://tbk.bbs.taobao.com/detail.html?appId=45301&postId=9334376 ）
         */
        @JSONField(name = "ysyl_commission_rate")
        private String ysylCommissionRate;
        /**
         * 预售有礼-预估淘礼金（元）
         */
        @JSONField(name = "ysyl_tlj_face")
        private String ysylTljFace;
        /**
         * 预售有礼-推广链接
         */
        @JSONField(name = "ysyl_click_url")
        private String ysylClickUrl;
        /**
         * 预售有礼-淘礼金使用结束时间
         */
        private String ysyl_tlj_use_end_time;
        private String ysyl_tlj_use_start_time;
        /**
         * 本地化-销售开始时间
         */
        private String sale_begin_time;
        private String sale_end_time;
        /**
         * 本地化-到门店距离（米）
         */
        private String distance;
        /**
         * 本地化-可用店铺id
         */
        private String usable_shop_id;
        /**
         * 本地化-可用店铺名称
         */
        private String usable_shop_name;
        /**
         * 活动价
         */
        private String sale_price;
        /**
         * ["每100减20","每200减50"]	跨店满减信息
         */
        private String kuadian_promotion_info;
        /**
         * 是否品牌精选，0不是，1是
         */
        private String superior_brand;
        /**
         * 比价场景专用，当系统检测到入参消费者ID购买当前商品会获得《天天开彩蛋》玩法的彩蛋时，该字段显示1，否则为0
         */
        private Integer reward_info;
        /**
         * 是否品牌快抢，0不是，1是
         */
        private String is_brand_flash_sale;
        /**
         * 本地化-扩展信息
         */
        private String localization_extend;
        /**
         * 物料评估-匹配分
         */
        private String match_score;
        /**
         * 物料评估-收益分
         */
        private String commi_score;
        /**
         * 是否是热门商品，0不是，1是
         */
        private String hot_flag;
        /**
         * 前N件佣金信息-前N件佣金生效或预热时透出以下字段
         */
        private TopNInfoDTO topn_info;
        /**
         * 百亿补贴信息
         */
        private BybtInfoDTO bybt_info;
        /**
         * 商品入驻淘特后产生的所有销量量级，不特指某段具体时间
         */
        private String tt_sold_count;

        /**
         * 猫超买返卡信息
         */
        private MaifanPromotionDTO maifan_promotion;

        /**
         * 额外奖励活动类型，如果一个商品有多个奖励类型，返回结果使用空格分割，0=预售单单奖励，1=618超级U选单单补
         */
        private String cpa_reward_type;

        /**
         * 额外奖励活动金额，活动奖励金额的类型与cpa_reward_type字段对应，如果一个商品有多个奖励类型，返回结果使用空格分割
         */
        private String cpa_reward_amount;

        /**
         * 合作伙伴单单补ID，用作“年货节超级单单补”活动合作伙伴奖励统计依据
         */
        private String activity_id;
        /**
         * 榜单url
         */
        private String rank_page_url;

        /**
         * 搜索类型
         */
        private String item_search_type;

        /**
         * 定向计划集合
         */
        private List<SpCampaign> sp_campaign_list;

    }

    @Getter
    @Setter
    public class SpCampaign{
        /**
         * 定向计划活动ID
         */
        private String sp_cid;
        /**
         * 定向计划名称
         */
        private String sp_name;
        /**
         * 定向佣金率
         */
        private String sp_rate;
        /**
         * 定向是否锁佣，0=不锁佣 1=锁佣
         */
        private String sp_lock_status;
        /**
         * 定向计划申请链接
         */
        private String sp_apply_link;
        /**
         * 定向计划是否可用 1-可用 0-不可用
         */
        private String sp_status;
    }

    @Getter
    @Setter
    public class MaifanPromotionDTO{
        /**
         * 猫超买返卡活动结束时间
         */
        private String maifan_promotion_end_time;
        private String maifan_promotion_start_time;
        /**
         * 猫超买返卡面额
         */
        private String maifan_promotion_discount;
        /**
         * 猫超买返卡总数，-1代表不限量，其他大于等于0的值为总数
         */
        private String maifan_promotion_condition;
    }

    @Getter
    @Setter
    public class BybtInfoDTO{
        /**
         * 百亿补贴品牌logo
         */
        private String bybt_brand_logo;
        /**
         * 百亿补贴白底图
         */
        private String bybt_pic_url;
        /**
         * 百亿补贴商品特征标签，eg.今日发货、晚发补偿、限购一件等
         */
        private List<String> bybt_item_tags;
        /**
         * 百亿补贴专属券面额，仅限百亿补贴场景透出
         */
        private String bybt_coupon_amount;
        /**
         * 百亿补贴页面实时价
         */
        private String bybt_show_price;
        /**
         * 全网对比参考价格
         */
        private String bybt_lowest_price;
        /**
         * 商品的百亿补贴开始时间
         */
        private String bybt_end_time;
        private String bybt_start_time;
    }

    @Getter
    @Setter
    public class TopNInfoDTO{
        /**
         * 前N件剩余库存
         */
        private Integer topn_quantity;
        private Integer topn_total_count;
        /**
         * 前N件佣金结束时间
         */
        private String topn_end_time;
        private String topn_start_time;
        /**
         * 百亿补贴信息
         */
        private String topn_rate;
    }

}
