package unicorn.mp.api.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.demo.common.encrypt.JsonParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.validation.BindException;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class JsonParamArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(JsonParam.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {

        JsonParam jsonParam = methodParameter.getParameterAnnotation(JsonParam.class);
        if (jsonParam == null) return null;
        String key = jsonParam.name();
        boolean require = jsonParam.require();

        HttpServletRequest servletRequest = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        if (servletRequest == null) {
            if (require) {
                throw new BindException(methodParameter, "参数绑定错误");
            }
            return null;
        }

        String value = servletRequest.getParameter(key);
        if (StringUtils.isEmpty(value)) {
            if (require) throw new BindException(methodParameter, "参数绑定错误");
            return null;
        }

        log.info("userInfo = {}", value);

        return JSONObject.parseObject(value, methodParameter.getParameterType());
    }
}
