package unicorn.mp.common.model.request.tb;

import unicorn.mp.common.model.request.SavvyBuyRequest;
import lombok.Data;

@Data
public class TbkItemOrderZtkQueryRequest extends SavvyBuyRequest {
    //时间类型，1表示按照订单支付时间获取，2表示按照订单更新时间获取，3表示按照订单编号获取。
    private String type = "3";
    private String page = "1";
    //每页数量，单页数最大50，默认50
    private String page_size;
    //订单开始时间,例子：2021-04-30 08:00:00
    private String startTime;
    private String endTime;
    //订单编号，必须输入完整的订单编号。
    private String orderid;
    //折淘客授权sid，无值，表示获取appkey下所有折淘客联盟订单。
    private String sid= "165690";
    //订单所属平台id，1美团、2考拉、3苏宁、4淘宝、5京东、6拼多多、7唯品会、8饿了么、9抖音等
    private String san_pingtai_id;

}
