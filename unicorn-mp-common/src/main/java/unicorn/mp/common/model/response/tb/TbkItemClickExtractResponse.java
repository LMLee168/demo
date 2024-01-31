package unicorn.mp.common.model.response.tb;

import unicorn.mp.common.model.response.TbResultResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TbkItemClickExtractResponse extends TbResultResponse {
    //商品id
    private String item_id;
    //商品混淆id
    private String open_id;
    //.1-动态ID转链场景，2-消费者比价场景，3-商品库导购场景（不填默认为1）
    private String biz_scene_id;
}
