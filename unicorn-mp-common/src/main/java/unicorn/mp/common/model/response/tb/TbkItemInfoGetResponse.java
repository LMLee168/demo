package unicorn.mp.common.model.response.tb;

import com.alibaba.fastjson.annotation.JSONField;
import unicorn.mp.common.model.response.TbResultResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TbkItemInfoGetResponse extends TbResultResponse {
    private List<NTbkItemDetailDTO> results;

    @Getter
    @Setter
    public class NTbkItemDetailDTO{
        /**
         * 一级类目名称： 女装
         */
        private String cat_name;
        /**
         * 商品ID
         */
        private String num_iid;
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
         * 商品信息-30天销量
         */
        private Integer volume;
        /**
         * 店铺信息-卖家id
         */
        @JSONField(name = "seller_id")
        private Long sellerId;
        /**
         * 店铺名称
         */
        private String nick;
        /**
         * 叶子类目名称
         */
        private String cat_leaf_name;
        /**
         * 是否加入消费者保障
         */
        private Boolean is_prepay;
        /**
         * 店铺信息-店铺dsr评分
         */
        @JSONField(name = "shop_dsr")
        private Integer shopDsr;
        /**
         * 卖家等级
         */
        private Integer ratesum;
        /**
         * 退款率是否低于行业均值
         */
        private Boolean i_rfd_rate;
        /**
         * 好评率是否高于行业均值
         */
        private Boolean h_good_rate;
        /**
         *成交转化是否高于行业均值
         */
        private Boolean h_pay_rate30;
        /**
         * 是否包邮
         */
        private Boolean free_shipment;
        /**
         * 商品库类型，支持多库类型输出，以英文逗号分隔“,”分隔，1:营销商品主推库，如果值为空则不属于1这种商品类型
         */
        private String material_lib_type;
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
         * 聚划算满减 -结束时间（毫秒）
         */
        private Long ju_play_end_time;
        private Long ju_play_start_time;
        /**
         * 1聚划算满减：满N件减X元，满N件X折，满N件X元） 2天猫限时抢：前N分钟每件X元，前N分钟满N件每件X元，前N件每件X元）
         */
        private String play_info;
        /**
         * 天猫限时抢可售 -结束时间（毫秒）
         */
        private Long tmall_play_activity_end_time;
        private Long tmall_play_activity_start_time;
        /**
         * 聚划算信息-聚淘开始时间（毫秒）
         */
        private String ju_online_start_time;
        private String ju_online_end_time;
        /**
         * 聚划算信息-商品预热开始时间（毫秒）
         */
        private String ju_pre_show_start_time;
        private String ju_pre_show_end_time;
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
         * 是否是热门商品，0不是，1是
         */
        private String hot_flag;
        /**
         * 输入的商品id
         */
        private String input_num_iid;


    }
}
