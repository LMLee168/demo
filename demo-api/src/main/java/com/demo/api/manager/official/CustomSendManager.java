package com.demo.api.manager.official;

import com.demo.common.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component("custom")
public class CustomSendManager extends AbstractSendManager{
    @Value("${miniprogram.appId}")
    private String miniprogramAppId;

    private static final String customSendUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=%s";

    @Override
    public String send(String openId, String content, String accountNo) {
        String result = null;

        try {
            result = HttpUtil.doPostJson(String.format(customSendUrl, getOfficialAccessToken(accountNo)), content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("客服消息发送 openId:{} ##result #{}", openId,result);
        return result;
    }
}
