package com.demo.common.model.response.tb;


import com.demo.common.model.response.TbResultResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TbkTpwdConvertResponse extends TbResultResponse {
    //商品Id
    private String num_iid;
    //商品淘客转链 https://s.click.taobao.com/t?e=xxx
    private String click_url;
    //店铺卖家ID
    private String seller_id;
    //入参淘口令对应原始链接
    private String origin_url;
    //入参淘口令推广链接中的pid，如果不属于当前调用的推广者则展示“0” :mm_1_1_1
    private String origin_pid;
    //1-动态ID转链场景，2-消费者比价场景，3-商品库导购场景
    private String biz_scene_id;
    //短口令
    private String model;
    //长口令
    private String password;
    //链接类型。1-单品，2-店铺，3-活动，0-其它
    private String url_type;
    //短链接
    private String short_url;






}
