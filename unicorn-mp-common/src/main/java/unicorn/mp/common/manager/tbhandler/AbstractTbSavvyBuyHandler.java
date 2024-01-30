package unicorn.mp.common.manager.tbhandler;

import com.alibaba.fastjson.JSON;
import unicorn.mp.common.enumation.ResponseUtil;
import unicorn.mp.common.manager.SavvyBuyConfigEnum;
import unicorn.mp.common.model.request.SavvyBuyRequest;
import unicorn.mp.common.model.response.TbResultResponse;
import unicorn.mp.common.utils.HttpUtil;
import unicorn.mp.common.utils.ResultWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

import static unicorn.mp.common.enumation.ResponseError.SYSTEM_ERROR;

@Slf4j
@Component
public abstract class AbstractTbSavvyBuyHandler<Request extends SavvyBuyRequest, Response extends TbResultResponse> implements TbSavvyBuyHandler<Request, Response> {

    private static final String GET = "GET";
    private static final String POST = "post";
    private static final String GET_JSON = "getJson";



    @Override
    public ResultWrapper<Response> sendRequestWrapper(Request request) {
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
            return ResponseUtil.error(SYSTEM_ERROR.getCode(),e.getMessage(),"");
        }
        Response response = buildResponse(jsonResult);
        return ResponseUtil.success(response);
    }


    protected abstract Map<String, Object> buildRequest(Request request);

    protected abstract Response buildResponse(String jsonResult);

    protected abstract SavvyBuyConfigEnum getWorkWxApiEnum();
}
