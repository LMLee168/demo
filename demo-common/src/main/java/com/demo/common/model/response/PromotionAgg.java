package com.demo.common.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PromotionAgg {

    //zhetaok 编号
    private String code;
    //分类id, 参考ztk分类
    private String type_one_id;
    //商品id
    private String tao_id;
    //短标题
    private String title;
    //描述
    private String jianjie;
    //主图
    private String pict_url;
    //是否京东自营，0是非京东自营，1是京东自营
    private String user_type;
    // /*卖家ID*/ /*店铺地址可自行拼接，https://mall.jd.com/index-953997.html */
    private String seller_id;
    //商品描述分
    private String shop_dsr;
    //月销量
    private String volume;
    //折扣价
    private String size;
    //券后价
    private String quanhou_jiage;
    //数据更新时间
    private String date_time_yongjin;
    //佣金比率
    private String tkrate3;
    //佣金类型
    private String yongjin_type;
    //优惠券ID
    private String coupon_id;
    //优惠券开始时间
    private String coupon_start_time;
    private String coupon_end_time;
    //优惠券金额
    private String coupon_info_money;
    //优惠券总数量，目前官方为0，无用
    private String coupon_total_count;
    //优惠券剩余数量，目前官方为0，无用
    private String coupon_remain_count;
    //优惠券信息
    private String coupon_info;
    //是否京东配送，1是
    private String juhuasuan;
    //是否京东拼购，1是
    private String taoqianggou;
    //是否海淘（京东国际），1是
    private String haitao;
    //是否京喜商品，1是
    private String jiyoujia;
    //是否京东好店，1是
    private String jinpaimaijia;
    //是否精选品牌，1是
    private String pinpai;
    //品牌名称
    private String pinpai_name;
    //是否有运费险，1有
    private String yunfeixian;
    //卖家昵称
    private String nick;
    //小图列表
    private String small_images;
    //白底图
    private String white_image;
    //长标题
    private String tao_title;
    //宝贝所在地
    private String provcity;
    //店铺名称
    private String shop_title;
    //视频地址
    private String zhibo_url;
    //网页实时总销量-废
    private String sellCount;
    //评论数量
    private String commentCount;
    //收藏数量
    private String favcount;
    //宝贝描述分
    private String score1;
    //卖家服务分
    private String score2;
    //物流服务分
    private String score3;
    //店铺评分
    private String creditLevel;
    //店铺logo
    private String shopIcon;
    //图文详情图片地址
    private String pcDescContent;
    //商品url
    //tb : 推广长链接，如果是渠道ID，请自行拼接上relationId的信息,否则订单信息中可能查不到渠道信息*/
    private String item_url;
    //叶子类目id
    private String category_id;
    //叶子类目name
    private String category_name;
    //一级类目id
    private String level_one_category_id;
    //一级类目name
    private String level_one_category_name;
    //返佣金额
    private String tkfee3;
    //店铺活动
    private String biaoqian;
    //朋友圈文案，需要自己进行urldecode
    private String tag;
    //数据添加时间
    private String date_time;
    //推广长链接
    private String coupon_click_url;
    //推广短链接
    private String shorturl;
    /*淘宝短链接2，此链接可以直接拉起手淘APP，只适用IOS。*/
    private String shorturl2;
    /*当入参special_id、relation_id、external_id时，该字段展示预估最低佣金率*/
    private String min_commission_rate;
    //tkl
    private String tkl;
}
