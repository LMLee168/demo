package unicorn.mp.common.model.request.tb;

import unicorn.mp.common.model.request.SavvyBuyRequest;
import lombok.Data;

@Data
public class TbkScPublisherInfoGetRequest extends SavvyBuyRequest {
    private Integer relationId;

    private Integer pageNo = 1;
    private Integer pageSize = 10;
    /**
     * 类型，必选 1:渠道信息；2:会员信息
     */
    private Integer infoType= 1;
    /**
     * 会员独占 - 会员运营ID
     */
    private String relationApp = "common";
    /**
     * 淘宝客外部用户标记，如自身系统账户ID；微信ID等
     */
    private String specialId;
    /**
     * 1-微信、2-微博、3-抖音、4-快手、5-QQ，0-其他；默认为0
     */
    private String externalType;
}
