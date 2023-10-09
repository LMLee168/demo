package com.demo.common.manager.jdhandler;

import com.alibaba.fastjson.JSON;
import com.demo.common.enumation.ResponseUtil;
import com.demo.common.manager.SavvyBuyConfigEnum;
import com.demo.common.model.request.SavvyBuyRequest;
import com.demo.common.model.response.jd.JdResultResponse;
import com.demo.common.utils.HttpUtil;
import com.demo.common.utils.ResultWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.demo.common.enumation.ResponseError.SYSTEM_ERROR;

@Slf4j
@Component
public abstract class AbstractJdSavvyBuyHandler<Request extends SavvyBuyRequest, Response extends JdResultResponse> implements JdSavvyBuyHandler<Request, Response> {

    private static final String Get = "GET";
    private static final String POST = "post";


    @Override
    public Response sendRequestWrapper(Request request) {
        SavvyBuyConfigEnum apiConfig = this.getWorkWxApiEnum();
        String url = apiConfig.getApi();
        log.info("tbk send request = {}, url = {}", JSON.toJSONString(request),url);
        Map<String, Object> params = buildRequest(request);
        String jsonResult = null;
        try {
            if (Get.equalsIgnoreCase(apiConfig.getMethod())) {
                jsonResult = HttpUtil.doGet(url,  params);
            }
            if (POST.equalsIgnoreCase(apiConfig.getMethod())) {
                jsonResult = HttpUtil.doPost(url, params);
            }
        } catch (Exception e) {
            log.error("request tbk error. url:{}, params:{}", url,
                    JSON.toJSONString(params), e);
        }
        log.info("result {}", jsonResult);
        Response response = buildResponse(jsonResult);
        return response;
    }


    protected abstract Map<String, Object> buildRequest(Request request);

    protected abstract Response buildResponse(String jsonResult);

    protected abstract SavvyBuyConfigEnum getWorkWxApiEnum();
}
