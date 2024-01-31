package unicorn.mp.common.model.response.jd;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PromotionCodeResp extends JdResultResponse{
    //生成的推广目标链接，以短链接形式，有效期60天
    private String shortURL;
    //生成推广目标的长链，长期有效
    private String clickURL;
    //需要权限申请，京口令（匹配到红包活动有效配置才会返回京口令）
    private String jCommand;
    //需要权限申请，短口令
    private String jShortCommand;
    //微信小程序ShortLink（需向cps-qxsq@jd.com申请权限）
    private String weChatShortLink;
}
