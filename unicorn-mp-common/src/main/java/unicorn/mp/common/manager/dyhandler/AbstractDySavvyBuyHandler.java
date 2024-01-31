package unicorn.mp.common.manager.dyhandler;

import com.alibaba.fastjson.JSON;
import unicorn.mp.common.manager.SavvyBuyConfigEnum;
import unicorn.mp.common.model.request.SavvyBuyRequest;
import unicorn.mp.common.model.response.dy.DyResultResponse;
import unicorn.mp.common.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public abstract class AbstractDySavvyBuyHandler<Request extends SavvyBuyRequest, Response extends DyResultResponse> implements DySavvyBuyHandler<Request, Response> {
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
