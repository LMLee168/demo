package com.demo.common.manager;

import lombok.Getter;

@Getter
public enum SavvyBuyConfigEnum {
    //邀请码
    INVITECODE("", "http://gw.api.taobao.com/router/rest", "taobao.tbk.sc.invitecode.get"),
    //私域用户
    INFO_SAVE("", "", "taobao.tbk.sc.publisher.info.save"),
    //淘宝客-公用-私域用户备案信息查询
    PUBLISHER_INFO_GET("GET", "", "taobao.tbk.sc.publisher.info.get"),
    //查询商品优惠券信息
    MATERIAL_OPTIONAL("GET", "http://gw.api.taobao.com/router/rest", "taobao.tbk.dg.material.optional"),
    //淘宝客商品链接转换
    ITEM_CONVERT("post", "", "taobao.tbk.item.convert"),
    // 淘宝客商品详情查询（简版）
    ITEM_INFO_GET("get", "", "taobao.tbk.item.info.get"),
    //手淘口令 解析--无商品id
    SHARE_TPWD("get", "http://gw.api.taobao.com/router/rest", "taobao.wireless.share.tpwd.query"),
    //淘客口令解析 id
    TPWD_CONVERT("get", "http://gw.api.taobao.com/router/rest", "taobao.tbk.tpwd.convert"),
    //长/段链 -》取商品id
    ITEM_CLICK_EXTRACT("get", "http://gw.api.taobao.com/router/rest", "taobao.tbk.item.click.extract"),



    //京东
    PROMOTION_GOODS_INFO("get", "https://api.jd.com/routerjson", "jd.union.open.goods.promotiongoodsinfo.query"),
    PROMOTION_BYSUBUNIONID("get","https://api.jd.com/routerjson","jd.union.open.promotion.bysubunionid.get"),
    GOODS_QUERY("get","https://api.jd.com/routerjson","jd.union.open.goods.query"),

    //zhetaoke GET/POST
    ZETAOKE_PROMOTION_BYSUBUNIONID("GET","http://api.zhetaoke.com:20000/api/open_jing_union_open_promotion_byunionid_get.ashx",""),

    //淘客口令解析 id
    AGG_ZTK_TPWD_CONVERT("post", "https://api.zhetaoke.com:10001/api/open_gaoyongzhuanlian_tkl.ashx", ""),
    AGG_ZTK_TPWD_CONVERT_STANDBY("GET", "http://api.zhetaoke.cn:10000/api/open_gaoyongzhuanlian_tkl.ashx", ""),
    ;

    private final String method;
    private final String api;
    private final String paramMethod;

    SavvyBuyConfigEnum(String method, String api, String paramMethod) {
        this.method = method;
        this.api = api;
        this.paramMethod = paramMethod;
    }
}
