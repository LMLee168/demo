package com.demo.common.model.request.jd;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PromotionCodeReq {
    //推广物料url，例如活动链接、商品链接、联盟链接（包含微信shortlink形式）、联盟商品ID（itemId）、联盟商品链接（jingfen.jd.com/detail/{itemId}.html）等
    private String materialId;
    //目标推客的联盟ID
    private Long unionId = 2011584216l;
    //子渠道标识，仅支持传入字母、数字、下划线或中划线，最多80个字符（不可包含空格），该参数会在订单行查询接口中展示（需申请权限，申请方法请见https://union.jd.com/helpcenter/13246-13247-46301）
    private String subUnionId;
    //推广位ID
    private Long positionId;
    //联盟子推客身份标识（不能传入接口调用者自己的pid） 618_618_6018
    private String pid;
    //优惠券领取链接，在使用优惠券、商品二合一功能时入参，且materialId须为商品详情页链接
    private String couponUrl;
    //转链类型（必填），1：长链， 2 ：短链 ，3： 长链+短链，默认短链，短链有效期60天
    private Integer chainType;
    //礼金批次号
    private String giftCouponKey;
    //渠道关系ID
    private Integer channelId;
    //是否生成短口令，1：生成，默认不生成（需申请权限，申请方法请见https://union.jd.com/helpcenter/13246-13247-46301）
    private Integer command;
    //微信小程序ShortLink类型（需向cps-qxsq@jd.com申请权限）
    private Integer weChatType;





}
