package unicorn.mp.common.model.request.jd;

import unicorn.mp.common.model.request.SavvyBuyRequest;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class UnionOpenGoodsPromotiongoodsinfoQueryRequest extends SavvyBuyRequest {
    /**京东skuID串，逗号分割，最多100个，开发示例如param_json={'skuIds':'5225346,7275691'}（非常重要 请大家关注：如果输入的sk串中某个skuID的商品不在推广中[就是没有佣金]，返回结果中不会包含这个商品的信息）
     *  https://item.m.jd.com/product/100053246844.html?utm_campaign=t_1001328990
     *  --->100053246844
     */
    @NonNull
    private String skuIds;
}
