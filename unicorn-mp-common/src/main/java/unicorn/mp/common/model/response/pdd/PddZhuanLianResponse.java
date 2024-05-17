package unicorn.mp.common.model.response.pdd;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PddZhuanLianResponse extends PddResultResponse{

    private ZhuanlianRes goods_zs_unit_generate_response;

    @Getter
    @Setter
    public static class ZhuanlianRes{
        /*推广短链接（唤起拼多多app）*/
        private String multi_group_mobile_short_url;
        /*双人团推广长链接*/
        private String multi_group_url;
        /*普通长链，微信环境下进入领券页点领券拉起小程序，
        */
        private String mobile_url;
        /*双人团推广短链接*/
        private String multi_group_short_url;
        /*对应出参mobile_url的短链接，与mobile_url功能一致*/
        private String mobile_short_url;
        /*推广长链接（可唤起拼多多app）*/
        private String multi_group_mobile_url;
        private String request_id;
        /*普通长链。微信环境下进入领券页点领券拉起小程序，浏览*/
        private String url;
        /*对应出参url的短链接，与url功能一致*/
        private String short_url;

    }
}
