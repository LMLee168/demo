package com.demo.common.model.request.dy;

import com.demo.common.model.request.SavvyBuyRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductShareRequest extends SavvyBuyRequest {
    /**
     * 商品URL/口令/短链接 必传
     */
    private String productUrl;
    //抖客PID :dy_123_233_2222 必传
    private String pid;
    //自定义字段，只允许 数字、字母和_，限制长度为40
    //必传
    private String externalInfo;
    //是否需要二维码，需要会导致响应耗时增加
    private Boolean needQrCode;
    //回流端标识 0：抖音 1：抖音极速版
    private Integer platform;
    //是否返回商品惠后价领券链接(如果商品有优惠则返回，否则不返回)
    private Boolean useCoupon;
    //是否返回站外H5链接
    private Boolean needShareLink;
    //团长参数
    private String insActivityParam;
    //是否需要zlink，需要会导致响应耗时增加，不填默认返回
    private Boolean needZlink;

}
