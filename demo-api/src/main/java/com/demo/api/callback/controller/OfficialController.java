package com.demo.api.callback.controller;

import com.demo.api.callback.util.AesException;
import com.demo.api.callback.util.WXPublicUtils;
import com.demo.api.manager.OfficialManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import static com.demo.common.utils.XmlUtils.bean2XmlString;
import static com.demo.common.utils.XmlUtils.xmlBody2map;

/**
 * 公众号回调
 */
@Slf4j
@RestController
@RequestMapping("/demo/v1/official")
public class OfficialController {

    public static final String SUCCESS = "success";
    public static final String ERROR = "error";

    public static final String TOKEN = "fd58c7d9-0fce-dd45-c834-6729ec795f2a";
    public static final String SUBSCRIBE = "subscribe";
    public static final String UNSUBSCRIBE = "unsubscribe";

    @Resource
    private OfficialManager officialManager;


    /**
     * 用户发送消息
     * @param request
     * @param accountNo
     * @return
     */
    @RequestMapping("/msg/{accountNo}")
    public String msg(HttpServletRequest request, @PathVariable("accountNo") String accountNo) {

        try {
            String method = request.getMethod();
            // get 方法为校验Token
            if ("GET".equals(method)) {
                String msgSignature = request.getParameter("signature");
                String msgTimestamp = request.getParameter("timestamp");
                String msgNonce = request.getParameter("nonce");
                String echostr = request.getParameter("echostr");
                if (WXPublicUtils.verifyUrl(TOKEN, msgSignature, msgTimestamp, msgNonce)) {
                    return echostr;
                }
                return ERROR;
            }

            String message = getRequestBody(request);
            Map<String, Object> xmlRequest = xmlBody2map(message, "xml");
            String openId = xmlRequest.get("FromUserName").toString();
            String developerId = xmlRequest.get("ToUserName").toString();
            String msgType = xmlRequest.get("MsgType").toString();
            String content = xmlRequest.get("Content").toString();
            return officialManager.dealMsg(openId, developerId, msgType, content);

        } catch (Exception e) {
            log.error("official accounts follow msg error", e);

        }
        return SUCCESS;
    }


    /**
     * 事件推送 关注/点击等
     * @param request
     * @param accountNo
     * @return
     */
    @RequestMapping("/event/{accountNo}")
    public String event(HttpServletRequest request, @PathVariable("accountNo") String accountNo) {
        boolean result = true;
        // get 方法为校验Token
        try {
            String method = request.getMethod();
            // get 方法为校验Token
            if ("GET".equals(method)) {
                String msgSignature = request.getParameter("signature");
                String msgTimestamp = request.getParameter("timestamp");
                String msgNonce = request.getParameter("nonce");
                String echostr = request.getParameter("echostr");
                if (WXPublicUtils.verifyUrl(TOKEN, msgSignature, msgTimestamp, msgNonce)) {
                    return echostr;
                }
                return ERROR;
            }
            String requestBody = getRequestBody(request);
            Map<String, Object> xmlRequest = xmlBody2map(requestBody, "xml");
            Object fromUserName = xmlRequest.get("FromUserName");
            Object eventObj = xmlRequest.get("Event");
            String openId;
            String event;
            if (fromUserName == null || eventObj == null) {
                // 暂不处理用户消息，回复空字符串
                return "";
            }
            openId = xmlRequest.get("FromUserName").toString();
            event = xmlRequest.get("Event").toString();
            String developerId = xmlRequest.get("ToUserName").toString();
            if (SUBSCRIBE.equals(event)) {
//                officialAccountsManager.subscribe(openId, accountNo);
//                return officialSendManager.officialReply(openId, accountNo, developerId);
//            } else if (UNSUBSCRIBE.equals(event)) {
//                result = officialAccountsManager.unsubscribe(openId, accountNo);
            } else {
                // 暂不处理用户消息，回复空字符串
                return "";
            }
        } catch (Exception e) {
            log.error("official accounts follow event error", e);
        }
        return result ? SUCCESS : ERROR;
    }

    private String getRequestBody(HttpServletRequest request) throws IOException {
        StringBuffer data = new StringBuffer();
        String line;
        BufferedReader reader;
        reader = request.getReader();
        while (null != (line = reader.readLine())) {
            data.append(line);
        }
        return data.toString();
    }


}
