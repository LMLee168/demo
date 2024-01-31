package unicorn.mp.common.model.response.jd;

import unicorn.mp.common.model.response.PromotionAgg;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ZheTaoKeAggPromotionGetResponse extends JdResultResponse{
    private Integer status;
    private List<PromotionAgg> content;


}
