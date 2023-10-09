package com.demo.common.model.request.tb;

import com.demo.common.model.request.SavvyBuyRequest;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class TbkItemConvertRequest extends SavvyBuyRequest {
    /**
     * 广告位id 区分效果位置
     */
    @NonNull
    private Long adzone_id;
    /**
     * 需返回的字段列表  num_iid,click_url
     */
    @NonNull
    private String fields;
    /**
     * 商品ID串，用','分割，从taobao.tbk.item.get接口获取num_iid字段，最大40个
     */
    @NonNull
    private String num_iids ="1";
    /**
     * 链接形式：1：PC，2：无线，默认：１
     */
    private Integer platform = 1;
    /**
     * 自定义输入串，英文和数字组成，长度不能大于12个字符，区分不同的推广渠道
     */
    private String unid;
    /**
     * 1表示商品转通用计划链接，其他值或不传表示转营销计划链接
     */
    private String dx;
}
