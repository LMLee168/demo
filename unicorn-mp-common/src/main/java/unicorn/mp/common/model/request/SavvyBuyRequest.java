package unicorn.mp.common.model.request;

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
    /**
     * 标准json类型，里面是业务参数按照参数名的字符串大小排序具体参数值，参考每个接口的参数表
     */
    private String param_json;
    private String sign;
    private String format;
    private Boolean simplify;

}
