package unicorn.mp.api.exception;

import lombok.Getter;

@Getter
public enum ResponseError {

    //和风天气code
    NOT_DATA(204, "查询地区暂无你需要的数据"),
    PARAM_ERROR(400, "请求参数或缺少必选的请求参数"),
    AUTH_FAIL(401, "认证失败"),
    REQ_LIMIT(402, "超过访问次数"),
    PERMISSION_ERROR(403, "无访问权限"),
    DATA_ERROR(404, "查询的数据或地区不存在。"),
    QPM_LIMIT(429, "超过限定的QPM"),
    SYSTEM_ERROR(500, "服务异常"),
    ;

    private final Integer code;

    private final String toast;

    ResponseError(Integer code, String toast) {
        this.code = code;
        this.toast = toast;
    }

    public static ResponseError getByCode(Integer code) {
        for (ResponseError error : values()) {
            if (error.getCode().equals(code)) {
                return error;
            }
        }
        return ResponseError.SYSTEM_ERROR;
    }
}
