package unicorn.mp.api.manager.weather.handler;

import com.alibaba.fastjson.JSON;
import unicorn.mp.api.model.request.ThirdRequest;
import unicorn.mp.api.model.response.ThirdResponse;
import com.demo.common.exception.StandardException;
import com.demo.common.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;

import java.util.Map;

import static com.demo.common.enumation.ResponseError.SYSTEM_ERROR;

@Slf4j
public abstract class AbstractThirdHandler<Request extends ThirdRequest, Response extends ThirdResponse> implements ThirdHandler<Request, Response> {

    @Override
    public Response sendRequest(Request request) {

        String result = null;
        HttpMethod method = this.httpMethod();
        Map<String, Object> params = buildRequest(request);
        String url = this.buildRequestUrl(request);
        try {
            if (HttpMethod.GET.equals(method)){
                result = HttpUtil.doGet(url, params);
            }
        } catch (Exception e) {
            log.error("request weather error. url:{}, params:{}", url, JSON.toJSONString(params), e);
            throw new StandardException(SYSTEM_ERROR.getCode(), "req weather error.url:" + url);
        }

        Response response = buildResponse(result);
        return response;
    }

    protected abstract String urlSuffix();

    protected abstract HttpMethod httpMethod();

    protected abstract Map<String, Object> buildRequest(Request request);

    protected abstract Response buildResponse(String jsonResult);

    protected abstract String buildRequestUrl(Request request);
}
