package unicorn.mp.common.model.response.tb;

import unicorn.mp.common.model.response.TbResultResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TbkItemConvertResponse extends TbResultResponse {
    /**
     * 淘宝客商品
     */
    private List<NTbkItem> results;

    @Getter
    @Setter
    public class NTbkItem{
        /**
         * t淘客地址
         */
        private String click_url;
        /**
         * 商品id
         */
        private String num_iid;
        /**
         * 原始输入的商品id
         */
        private String input_num_iid;

    }
}
