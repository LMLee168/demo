package unicorn.mp.common.model.request.tb;

import unicorn.mp.common.model.request.SavvyBuyRequest;
import lombok.Data;
import lombok.NonNull;

@Data
public class TbkItemInfoGetRequest extends SavvyBuyRequest {
    /**
     * 商品ID串，用,分割，最大40个
     */
    @NonNull
    private String num_iids;
    /**
     * 链接形式：1：PC，2：无线，默认：１
     */
    private Integer platform = 1;
    /**
     * ip地址，影响邮费获取，如果不传或者传入不准确，邮费无法精准提供
     */
    private String ip;
    /**
     * 1-动态ID转链场景，2-消费者比价场景，3-商品库导购场景（不填默认为1）
     */
    private String biz_scene_id = "1";
    /**
     * 1-自购省，2-推广赚（代理模式专属ID，代理模式必填，非代理模式不用填写该字段）
     */
    private String promotion_type;
    /**
     * 渠道关系ID
     */
    private String relation_id;
    /**
     * 商品库服务账户(场景id3权限对应的memberid）
     */
    private Integer manage_item_pub_id;

}
