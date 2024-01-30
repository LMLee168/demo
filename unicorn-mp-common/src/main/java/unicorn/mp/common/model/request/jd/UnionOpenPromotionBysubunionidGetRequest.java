package unicorn.mp.common.model.request.jd;

import unicorn.mp.common.model.request.SavvyBuyRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnionOpenPromotionBysubunionidGetRequest extends SavvyBuyRequest {

    private PromotionCodeReq promotionCodeReq;
}
