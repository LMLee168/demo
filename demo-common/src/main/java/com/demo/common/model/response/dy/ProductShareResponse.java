package com.demo.common.model.response.dy;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductShareResponse extends DyResultResponse{
    /**
     * 1:携带，商品链接或口令是否携带了团长参数，最终佣金以订单为准
     */
    private String use_ins_activity;
    /**
     * 商品站外H5链接
     */
    private String share_link;
    /**
     * 抖口令
     */
    private String dy_password;
    /**
     * 二维码
     */
    private QrCode qr_code;
    //deeplink
    private String dy_deeplink;
    //Zlink，允许外部APP直接唤起抖音直播间，未安装抖音应用用户，唤起抖音下载页，有效期60天
    private String dy_zlink;
    private CouponInfo coupon_link;

    @Getter
    @Setter
    public static class CouponInfo{
        //是否有优惠价&优惠券 0有优惠券 1没有优惠券
        private Integer coupon_status;
        private QrCode qrcode;
        //口令
        private String share_command;
        //deeplink
        private String deeplink;
        //站外H5领券链接
        private String share_link;
    }

    @Getter
    @Setter
    public static class QrCode{
        //图片地址
        private String url;
        private Integer width;
        private Integer height;
    }












}
