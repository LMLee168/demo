package unicorn.mp.common.model.request.pdd;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import unicorn.mp.common.model.request.SavvyBuyRequest;

@Data
public class PddSavvyBuyRequest extends SavvyBuyRequest {

    @JSONField(name = "pdd_app_key")
    private String pddAppKey;
    @JSONField(name = "pdd_app_secret")
    private String pddAppSecret;
    //pdd推广为id
    private String pid = "25260681_268550228";
    //支持纯数字id，或者拼多多推广短链。
    private String content;
    /**
     * 否	自定义参数，为链接打上自定义标签；自定义参数最长限制64个字节；格式为： {"uid":"15611448080","sid":""} ，其中 uid 用户唯一标识，可自行加密后传入，每个用户仅且对应一个标识，必填；
     * 重要的事情说三遍：该参数需要进行Urlencode编码！该参数需要进行Urlencode编码！该参数需要进行Urlencode编码！
     * 重要的事情说三遍：返利才需要此参数，导购不需要！返利才需要此参数，导购不需要！返利才需要此参数，导购不需要！
     *
     */
    private String custom_parameters;

}
