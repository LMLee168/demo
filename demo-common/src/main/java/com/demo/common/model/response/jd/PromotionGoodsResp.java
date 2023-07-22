package com.demo.common.model.response.jd;

import com.demo.common.model.bo.BaseDO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PromotionGoodsResp extends BaseDO {
    //商品ID
    private String skuId;
    //商品单价即京东价
    private BigDecimal unitPrice;
    //商品落地页-- 推广的物料链接
    private String materialUrl;
    //推广结束日期（日期对应的时间戳，包含当天，例：1668096000000，转换后日期为2022-11-11，包含2022-11-11当天）
    private Long endDate;
    //是否支持运费险(1:是,0:否)
    private Integer isFreeFreightRisk;
    //是否包邮(1:是,0:否,2:自营商品遵从主站包邮规则)
    private Integer isFreeShipping;
    //无线佣金比例
    private BigDecimal commisionRatioWl;
    //PC佣金比例
    private BigDecimal commisionRatioPc;
    //图片地址
    private String imgUrl;
    //商家ID
    private Long vid;
    //一级类目名称
    private String cidName;
    //商品无线京东价（单价为-1表示未查询到该商品单价）
    private BigDecimal wlUnitPrice;
    //二级类目名称
    private String cid2Name;
    //是否秒杀(1:是,0:否)
    private Integer isSeckill;
    //二级类目ID
    private Integer cid2;
    private String cid3Name;
    //30天引单数量
    private Integer inOrderCount;
    private Integer cid3;
    //店铺ID
    private Long shopId;
    //是否自营(1:是,0:否)
    private Integer isJdSale;
    //商品名称
    private String goodsName;
    //推广开始日期（时间戳，毫秒）
    private Long startDate;
    private Integer cid;







}
