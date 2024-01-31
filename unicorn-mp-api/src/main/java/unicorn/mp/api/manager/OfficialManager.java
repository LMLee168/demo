package unicorn.mp.api.manager;

import unicorn.mp.api.manager.savvybuy.SavvyBuyChannelEnum;
import unicorn.mp.api.manager.savvybuy.SavvyBuyProvider;
import unicorn.mp.common.enumation.OfficialMsgTypeEnum;
import unicorn.mp.common.model.request.official.ReplyTextRequest;
import unicorn.mp.common.utils.DateUtils;
import unicorn.mp.common.utils.XmlUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Component
public class OfficialManager {

    private static final String defaultMsg = "查询失败，该商品暂无优惠！" ;

    @Resource
    private Map<String, SavvyBuyProvider> savvyBuyProviderMap;

    public String dealMsg(String openId, String developerId, String msgType, String content) {
        if (!OfficialMsgTypeEnum.TEXT.getType().equals(msgType)) {
            return "";
        }
        SavvyBuyProvider  provider = getSavvyBuyProvider(content);
        if (provider == null) return "";
        //先发送个消息再回复
        //to do ...
        //处理查询逻辑
        String couponMsg =  provider.queryCoupon(content);
        if (StringUtils.isBlank(couponMsg)) {
            couponMsg = defaultMsg;
        }
        return msgText(openId, developerId, couponMsg);
    }

    private SavvyBuyProvider getSavvyBuyProvider(String msgContent) {
        if (msgContent.contains(SavvyBuyChannelEnum.JD.getDomain())) {
            return savvyBuyProviderMap.get(SavvyBuyChannelEnum.JD.getChannel());
        }
        if (msgContent.contains(SavvyBuyChannelEnum.TB.getDomain())) {
            return savvyBuyProviderMap.get(SavvyBuyChannelEnum.TB.getChannel());
        }
        if (msgContent.contains(SavvyBuyChannelEnum.PDD.getDomain())) {
            return savvyBuyProviderMap.get(SavvyBuyChannelEnum.PDD.getChannel());
        }
        return null;
    }

    private String msgText(String fromUserName, String toUserName, String content){
        ReplyTextRequest textMsg = new ReplyTextRequest();
        textMsg.setFromUserName(fromUserName);
        textMsg.setToUserName(toUserName);
        textMsg.setCreateTime(DateUtils.getTimestamp(LocalDateTime.now()));
        textMsg.setContent(content);
        return XmlUtils.bean2XmlString(textMsg);
    }
}
