package unicorn.mp.common.model.response.ele;

import unicorn.mp.common.model.bo.BaseDO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetElemePromotionOfficcialActivityResponse extends BaseDO {
    private String message;
    private String request_id;
    private Integer result_code;
    private ElemePromotionOfficcialActivity alibaba_alsc_union_eleme_promotion_officialactivity_get_response;

    @Getter
    @Setter
    public static class ElemePromotionOfficcialActivity{
        private String description;
        private Long end_time;
        private String id;
        private String picture;
        private Long start_time;
        private String title;
        private Link link;

    }

    @Getter
    @Setter
    public static class Link{
        private String alipay_mini_url;
        private String ele_scheme_url;
        private String h5_short_link;
        private String h5_url;
        private String mini_qrcode;
        private String picture;
        private String tb_mini_qrcode;
        private String tb_qr_code;
        private String wx_appid;
        private String wx_path;
    }


}
