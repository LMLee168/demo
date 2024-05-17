package unicorn.mp.common.model.response.pdd;

import lombok.Getter;
import lombok.Setter;
import unicorn.mp.common.model.bo.BaseDO;

@Getter
@Setter
public class PddResultResponse extends BaseDO {
    /**
     * 200:success; 500:服务端异常; 400:参数错误; 401:验证失败; 403:无访问权限; 404数据不存在; 409:数据已存在; 410:联盟用户不存在，请检查unionId是否正确; 411:unionId不正确，请检查unionId是否正确; 2003101:参数异常，skuIds为空; 2003102:参数异常，sku个数为1-100个; 2003103:接口异常，没有相关权限; 2003104:请求商品信息{X}条，成功返回{Y}条, 失败{Z}条;
     */
    private Integer code;
    private String message;
}
