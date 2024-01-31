package unicorn.mp.common.model.response.jd;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PromotionQueryResult extends JdResultResponse{
    private String requestId;

    private List<PromotionGoodsResp> data;

}
