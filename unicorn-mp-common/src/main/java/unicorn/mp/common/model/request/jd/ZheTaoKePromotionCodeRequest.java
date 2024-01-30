package unicorn.mp.common.model.request.jd;

import unicorn.mp.common.model.request.SavvyBuyRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ZheTaoKePromotionCodeRequest extends SavvyBuyRequest {

    private PromotionCodeReq promotionCodeReq;

    //默认0，signurl=0，返回官方转链结果
    //signurl=5，返回结果整合京东商品详情API和京东转链API，调用一次接口，获取商品详情、商品优惠券信息和商品转链结果。
    //注意，有些是活动链接或者非商品链接，这时候即使传入signurl=5，也只返回官方原版转链结果，所以请大家自己需要做好代码兼容。
    private String signurl = "0";
    //推广物料url，例如活动链接、商品链接等；支持仅传入skuid
}
