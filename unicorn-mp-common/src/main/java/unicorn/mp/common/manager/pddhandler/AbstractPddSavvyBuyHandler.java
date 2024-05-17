package unicorn.mp.common.manager.pddhandler;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import unicorn.mp.common.enumation.ResponseUtil;
import unicorn.mp.common.manager.SavvyBuyConfigEnum;
import unicorn.mp.common.model.request.pdd.PddSavvyBuyRequest;
import unicorn.mp.common.model.response.pdd.PddResultResponse;
import unicorn.mp.common.utils.HttpUtil;

import java.util.Map;

import static unicorn.mp.common.enumation.ResponseError.SYSTEM_ERROR;

@Slf4j
@Component
public abstract class AbstractPddSavvyBuyHandler<Request extends PddSavvyBuyRequest, Response extends PddResultResponse> implements PddSavvyBuyHandler<Request, Response> {

    private static final String GET = "GET";
    private static final String POST = "post";
    private static final String GET_JSON = "getJson";



    @Override
    public Response sendRequestWrapper(Request request) {
        SavvyBuyConfigEnum apiConfig = this.getWorkWxApiEnum();
        String url = apiConfig.getApi();
        log.info("tbk send request = {}, url = {}", JSON.toJSONString(request),url);
        Map<String, Object> params = buildRequest(request);
        String jsonResult = null;
        try {
            if (GET.equalsIgnoreCase(apiConfig.getMethod())) {
                jsonResult = HttpUtil.doGet(url,  params);
            }
            if (POST.equalsIgnoreCase(apiConfig.getMethod())) {
                jsonResult = HttpUtil.doPost(url, params);
            }
            if (GET_JSON.equalsIgnoreCase(apiConfig.getMethod())) {
                jsonResult = HttpUtil.doGetJson(url, params);
            }
        } catch (Exception e) {
            log.error("request tbk error. url:{}, params:{}", url,
                    JSON.toJSONString(params), e);
        }
        Response response = buildResponse(jsonResult);
        return response;
    }


    protected abstract Map<String, Object> buildRequest(Request request);

    protected abstract Response buildResponse(String jsonResult);

    protected abstract SavvyBuyConfigEnum getWorkWxApiEnum();
}
