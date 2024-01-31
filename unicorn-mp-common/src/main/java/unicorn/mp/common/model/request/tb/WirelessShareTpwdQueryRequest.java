package unicorn.mp.common.model.request.tb;

import unicorn.mp.common.model.request.SavvyBuyRequest;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class WirelessShareTpwdQueryRequest extends SavvyBuyRequest {
    /**
     *  淘口令:【天猫品牌号】，复制这条信息￥sMCl0Yra3Ae￥后打开手机淘宝
     */
    @NonNull
    private String password_content;
}
