package unicorn.mp.api.interceptor;

import com.alibaba.fastjson.JSONObject;
import unicorn.mp.common.encrypt.IgnoreLogin;
import unicorn.mp.common.enumation.ResponseError;
import unicorn.mp.common.model.bo.DeviceInfoBO;
import unicorn.mp.common.utils.ErrorResponseGenerator;
import unicorn.mp.common.utils.NetworkUtil;
import unicorn.mp.common.utils.ResultWrapper;
import unicorn.mp.common.utils.UserStore;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {

            String accessToken = request.getHeader("accessToken");
            if (!(handler instanceof HandlerMethod)) {
                return true;
            }

            DeviceInfoBO deviceInfoBO = handleDeviceInfo(request);
            UserStore.getInstance().setDevice(deviceInfoBO);
            String userIp = NetworkUtil.getIpAddr(request);
            UserStore.getInstance().setUserIp(userIp);
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            IgnoreLogin methodIgnoreLogin = handlerMethod.getMethodAnnotation(IgnoreLogin.class);
            IgnoreLogin typeIgnoreLogin = handlerMethod.getBeanType().getAnnotation(IgnoreLogin.class);
            if (methodIgnoreLogin != null || typeIgnoreLogin != null) {
                log.info("拦截到 " + request.getRequestURI() + "请求，忽略校验登录状态！");
                if (!StringUtils.isEmpty(accessToken)) {
                    ResultWrapper<?> resultWrapper = null;//tokenManager.verifyAccessToken(accessToken);
                    if (resultWrapper.isFailure()) {
                        log.warn("用户token校验失败, result = {}, path = {}, params = {}, deviceInfo = {}",
                                JSONObject.toJSONString(resultWrapper),
                                request.getRequestURI(),
                                JSONObject.toJSONString(request.getParameterMap()),
                                JSONObject.toJSONString(deviceInfoBO));
                        ErrorResponseGenerator.generateErrorMessage(resultWrapper, response);
                        UserStore.getInstance().remove();
                        return false;
                    }
                    Long userId = 0L;//resultWrapper.getData().getUserId();
                    UserStore.getInstance().set(userId);
                    request.setAttribute("userId", userId);
                }

                return true;
            }

            if (StringUtils.isEmpty(accessToken)) {
                ErrorResponseGenerator.generateErrorMessage(ResponseError.SYSTEM_ERROR,response);
                UserStore.getInstance().remove();
                return false;
            }

            ResultWrapper<?> resultWrapper = null;//tokenManager.verifyAccessToken(accessToken);
            if (resultWrapper.isFailure()) {
                log.warn("用户token校验失败, result = {}, path = {}, params = {}, deviceInfo = {}",
                        JSON.toJSONString(resultWrapper),
                        request.getRequestURI(),
                        JSON.toJSONString(request.getParameterMap()),
                        JSON.toJSONString(deviceInfoBO));
                ErrorResponseGenerator.generateErrorMessage(resultWrapper, response);
                UserStore.getInstance().remove();
                return false;
            }
            Long userId = 0l;//resultWrapper.getData().getUserId();
            UserStore.getInstance().set(userId);
            request.setAttribute("userId", userId);
            log.info("拦截到:{},userId:{},参数:{}",request.getRequestURI(), userId, JSON.toJSONString(request.getParameterMap()));

            return true;
        }catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        UserStore.getInstance().remove();
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        log.info("invoking process...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        log.info("completed process...");
        UserStore.getInstance().remove();
    }

    private DeviceInfoBO handleDeviceInfo(HttpServletRequest request) {
        String version = request.getParameter("version");
        String deviceType = request.getParameter("deviceType");
        String platform = request.getParameter("platform");
        String deviceId = request.getParameter("deviceId");
        String blackBox = request.getParameter("blackBox");
        String tanshuDeviceId = request.getParameter("tanshuDeviceId");
        if ("miniApp".equals(platform) && "devtools".equals(deviceType)){
            deviceType = "android";
        }
        if (StringUtils.isBlank(blackBox)){
            blackBox = request.getHeader("blackBox");
        }

        DeviceInfoBO bo = new DeviceInfoBO();
        bo.setVersion(version);
        bo.setDeviceType(deviceType);
        bo.setPlatform(platform);
        bo.setDeviceId(deviceId);
        return bo;
    }
}