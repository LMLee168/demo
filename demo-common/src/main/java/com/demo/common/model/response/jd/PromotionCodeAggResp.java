package com.demo.common.model.response.jd;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PromotionCodeAggResp extends PromotionGoodsResp{
    private PromotionCodeResp promotionCodeResp;
    //返佣金额
    private String tkfee3;
    //商品url
    private String item_url;
    //优惠券金额
    private String coupon_info_money;
    //优惠券ID
    private String coupon_id;
    //券后价
    private String quanhou_jiage;
}
