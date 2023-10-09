package com.demo.common.model.request;

import lombok.Data;

@Data
public class SavvyBuyRequest {
    /**
     * 接口名称
     */
    private String method;
    /**
     * 应用key
     */
    private String app_key;
    /**
     * 需授权时必传
     */
    private String session;
    /**
     * 时间戳
     */
    private String timestamp;
    /**
     * api 协议版本 2.0
     */
    private String v;
    /**
     * 签名
     */
    private String sign_method;
    private String sign;
    private String format;
    private Boolean simplify;

}
