package unicorn.mp.common.model.request.tb;

import unicorn.mp.common.model.request.SavvyBuyRequest;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class TbkTpwdConvertRequest extends SavvyBuyRequest {
    /**
     * ￥2k12308DjviP￥	需要解析的淘口令
     */
    @NonNull
    private String pwd_content;
    @NonNull
    public Long adzone_id=111482500415l;
    /**
     * 1表示商品转通用计划链接，其他值或不传表示优先转营销计划链接
     */
    private String dx;
    /**
     * 会员人群ID，转链后会自动带上，可用于统计人群推广效果
     */
    private Integer ucrowd_id;
    /**
     * 渠道id
     */
    private String relation_id;



}
