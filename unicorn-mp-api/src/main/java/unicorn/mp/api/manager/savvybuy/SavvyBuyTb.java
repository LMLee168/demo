package unicorn.mp.api.manager.savvybuy;

import unicorn.mp.common.manager.CommonTbManager;
import unicorn.mp.common.model.response.jd.PromotionCodeAggResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.regex.Pattern;

@Slf4j
@Component("tb")
public class SavvyBuyTb implements SavvyBuyProvider{

    private static final String zhetaokeKey = "8bb75e5f298849a2901f19b2983708cc";
    private static String tklToProUrl = "https://api.zhetaoke.com:10001/api/open_shangpin_id.ashx";

    @Resource
    private CommonTbManager commonTbManager;
    private static StringBuilder sb = new StringBuilder();

    @Override
    public String queryCoupon(String queryContent) {

        String tkl = getKey(queryContent);
        PromotionCodeAggResp aggResp = commonTbManager.tpwdConvertAggByZtk(tkl);

        sb.append(aggResp.getGoodsName()).append("\n")
                .append("【在售价】：").append(aggResp.getUnitPrice()).append("\n");
        if (aggResp.getQuanhou_jiage() != null) {
            sb.append("【券后价】：").append(aggResp.getQuanhou_jiage()).append("\n");
        }
        if (aggResp.getTkfee3() != null) {
            sb.append("【收货返现】：").append(aggResp.getTkfee3()).append("\n");
        }

        sb.append("【下单链接】:").append(aggResp.getPromotionCodeResp().getShortURL()).append("\n")
                .append("打开上面链接下单/加入购物车，确认收货后即可返现哟\n" +
                        "更多商品优惠活动\uD83D\uDC47可点击菜单栏【唯品会精选】查看第一时间购买到最优惠的商品\n" +
                        "-------------------------------\n" +
                        "注意：除本公众号领取的优惠券使用其他会导致仮莉失败\n" +
                        "❗成功下单后5-10分钟后会收到返利提醒，若没收到信息可发送订单号查询");
        return sb.toString();
    }

    //获得tkl
    private String getKey(String content) {
        String pattern = "؋‎฿₿¢₡₵$₫֏€₲₾₴₭₺₼₥₦₱£﷼‎៛₽₨௹₹৲৳૱₪₸₮₩¥₳₠₢₯₣₤₶₧₰₷";
        pattern = "([" + pattern + "])" + "(\\w+)\\1";
        return Pattern.compile(pattern).matcher(content).toString();
    }
}
