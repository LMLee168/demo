package com.demo.common.model.request.tb;

import com.demo.common.model.request.SavvyBuyRequest;
import lombok.Data;
import lombok.NonNull;

@Data
public class TbkScPublisherInfoSaveRequest extends SavvyBuyRequest {

    /**
     * 渠道备案 - 来源，取链接的来源
     */
    private String ralationFrom;
    /**
     * 渠道备案 - 线下场景信息，1 - 门店，2- 学校，3 - 工厂，4 - 其他
     */
    private String offlineScene;
    /*
    *渠道备案 - 线上场景信息，1 - 微信群，2- QQ群，3 - 其他
     */
    private String onlineScene;
    /**
     * 淘宝客邀请渠道或会员的邀请码
     */
    @NonNull
    private String inviterCode;
    /**
     * 类型，必选 默认为1:
     */
    @NonNull
    private Integer infoType;
    /**
     * 媒体侧渠道备注
     */
    private String note;
    /**
     * 线下备案注册信息,字段包含: 电话号码(phoneNumber，必填),省(province,必填),市(city,必填),区县街道(location,必填),详细地址(detailAddress,必填),经营类型(career,线下个人必填),店铺类型(shopType,线下店铺必填),店铺名称(shopName,线下店铺必填),店铺证书类型(shopCertifyType,线下店铺选填),店铺证书编号(certifyNumber,线下店铺选填)
     * {"phoneNumber":"18801088599","city":"江苏省","province":"南京市","location":"玄武区花园小区","detailAddress":"5号楼3单元101室","shopType":"社区店","shopName":"全家便利店","shopCertifyType":"营业执照","certifyNumber":"111100299001"}
     */
    private String registerInfo;
}
