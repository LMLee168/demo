package com.demo.common.model.response.tb;

import com.demo.common.model.response.TbResultResponse;
import lombok.Data;

import java.util.List;

@Data
public class TbkItemOrderZtkQueryResponse extends TbResultResponse {

    private List<ContentBO> content;

    @Data
    public static class ContentBO {
        /*折淘客自动增长列*/
        private String code;
        /*折淘客账号*/
        private String account;
        /*折淘客appkey*/
        private String appkey_ztk;
        /*原始推广位sid*/
        private String sid;
        /*折淘客是否结算，0未结算，1已结算*/
        private String is_jiesuan;
        /*折淘客结算时间*/
        private String jiesuan_time;
        /*折淘客结算金额*/
        private String jiesuan_profit;
        /*订单编号*/
        private String orderid;
        /*订单支付时间*/
        private String paytime;
        /*订单实际支付金额*/
        private String payprice;
        /*预估佣金*/
        private String profit;
        /*订单标题*/
        private String smstitle;
        /*商品编号，可能为空*/
        private String item_id;
        /*退款金额*/
        private String refundprice;
        /*退款时间*/
        private String refundtime;
        /*订单状态，1已付款，8已完成，9已退款或风控*/
        private String status;
        /*数据最后更新时间*/
        private String update_time;
        /*订单类型*/
        private String type;
        /*折淘客授权sid*/
        private String sid_ztk;
        /*推广者自定义编号*/
        private String customer_id_ztk;
        /*推广者平台类型，默认zhetaoke*/
        private String platform_ztk;
        /*活动id*/
        private String actId;
        private String san_pingtai;
        /*订单所属平台id，1美团、2考拉、3苏宁、4淘宝、5京东、6拼多多、7唯品会、8饿了么、9抖音等*/
        private String san_pingtai_id;
    }
}
