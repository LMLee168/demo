package unicorn.mp.api.model.response;

import com.demo.common.model.bo.BaseDO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SavvyBuyResponse extends BaseDO {

    //商品名称
    private String title;
    //推广物料链接
    private String clickUrl;
    //转链后短链
    private String shortUrl;
    //原价
    private double originPrice;
    //返利
    private Integer commision;
    //券后价
    private double disPrice;

}
