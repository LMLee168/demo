package unicorn.mp.api.callback.controller;

import unicorn.mp.api.callback.util.WXBizMsgCrypt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import static unicorn.mp.common.utils.XmlUtils.xmlBody2map;

/**
 * 企微回调
 */
@Slf4j
@RestController
@RequestMapping("/unicorn/v1/workwx")
public class WorkWxController {

    @Value("${wecom.corpid}")
    private String CORP_ID;
    @Value("${wecom.token}")
    private String TOKEN;
    @Value("${wecom.encodingaeskey}")
    private String ENCODING_AES_KEY;
    private static final String SUCCESS = "";

    @RequestMapping("/callback")
    public String urlVerify(HttpServletRequest request) {
        String msgSignature = request.getParameter("msg_signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        try {
            WXBizMsgCrypt crypt = new WXBizMsgCrypt(TOKEN, ENCODING_AES_KEY, CORP_ID);
            String method = request.getMethod();
            if ("GET".equals(method)) {
                String res = crypt.VerifyURL(msgSignature, timestamp, nonce, echostr);
                return res;
            }
            String requestBody = getRequestBody(request);
            String postData = crypt.DecryptMsg(msgSignature, timestamp, nonce, requestBody);
            Map<String, Object> xmlRequest = xmlBody2map(postData, "xml");
//            WorkWxCallbackDTO callbackDTO = new WorkWxCallbackDTO();
//            callbackDTO.setXmlRequestParams(xmlRequest);
        }catch (Exception e) {
            log.error("workwx callback resolve error", e);
        }
        return SUCCESS;
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
