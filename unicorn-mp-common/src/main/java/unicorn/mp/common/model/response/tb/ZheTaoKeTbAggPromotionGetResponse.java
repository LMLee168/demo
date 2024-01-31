package unicorn.mp.common.model.response.tb;

import unicorn.mp.common.model.response.PromotionAgg;
import unicorn.mp.common.model.response.TbResultResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ZheTaoKeTbAggPromotionGetResponse extends TbResultResponse {
    private List<PromotionAgg> content;
    private Integer status;
//    /*后台一级类目*/
//    private Long category_id;
//    /*二合一推广链接，已经自动拼接S券*/
//    private String coupon_click_url;
//    /*优惠券结束时间*/
//    private String coupon_end_time;
//    /*优惠券面额*/
//    private String coupon_info;
//    /*优惠券剩余量*/
//    private Long coupon_remain_count;
//    /*优惠券开始时间*/
//    private String coupon_start_time;
//    /*优惠券总量*/
//    private Long coupon_total_count;
//    /*优惠券(商品优惠券推广链接中的券)类型，1 公开券，2 私有券，3 妈妈券*/
//    private Integer coupon_type;
//    /*商品id*/
//    private String item_id;
//    /*推广长链接，如果是渠道ID，请自行拼接上relationId的信息,否则订单信息中可能查不到渠道信息*/
//    private String item_url;
//    /*佣金比率(%)*/
//    private String max_commission_rate;
//    /*当入参special_id、relation_id、external_id时，该字段展示预估最低佣金率(%)*/
//    private String min_commission_rate;
//    /*特殊券ID，拼接规则参考下方文字*/
//    private String s_coupon_id;
//    /*特殊券面额*/
//    private String s_coupon_amount;
//    /*特殊券最低使用金额*/
//    private String s_coupon_startfee;
//    /*特殊券开始时间*/
//    private String s_coupon_start_time;
//    /*特殊券结束时间*/
//    private String s_coupon_end_time;
//    /*叶子类目名称*/
//    private String cat_leaf_name;
//    /*一级类目名称*/
//    private String cat_name;
//    /*卖家昵称*/
//    private String nick;
//    /*商品主图*/
//    private String pic_url;
//    /*宝贝所在地*/
//    private String provcity;
//    /*卖家ID*/
//    private String seller_id;
//    /*商品小图列表*/
//    private String small_images;
//    /*商品长标题*/
//    private String title;
//    /*是否天猫，0是淘宝，1是天猫*/
//    private String user_type;
//    /*月销量*/
//    private String volume;
//    /*折扣价*/
//    private String zk_final_price;
//    /*淘宝短链接*/
//    private String shorturl;
//    /*淘宝短链接2，此链接可以直接拉起手淘APP，只适用IOS。*/
//    private String shorturl2;
//    private String tkl;



}
