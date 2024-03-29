package unicorn.mp.common.enumation;

import unicorn.mp.common.utils.ResultWrapper;

public class ResponseUtil {

    public static <T> ResultWrapper<T> success(T data) {
        ResultWrapper<T> result = success("操作成功",data);
        return result;
    }

    public static <T> ResultWrapper<T> success(String code, String message, T data) {
        ResultWrapper<T> result = new ResultWrapper<T>();
        result.setSuccess(true);
        result.setCode(code);
        result.setData(data);
        result.addMessage(message);
        return result;
    }

    public static <T> ResultWrapper<T> success(String message, T data) {
        ResultWrapper<T> result = new ResultWrapper<T>();
        result.setSuccess(true);
        result.setCode("0");
        result.setData(data);
        result.addMessage(message);
        return result;
    }

    public static <T> ResultWrapper<T> error(String code, String message, String toast) {
        ResultWrapper<T> result = new ResultWrapper<T>();
        result.setSuccess(false);
        result.setCode(code);
        result.addMessage(message);
        result.setToast(toast);
        return result;
    }

    public static <T> ResultWrapper<T> error(ResponseError err) {
        ResultWrapper<T> result = new ResultWrapper<T>();
        result.setSuccess(false);
        result.setCode(err.getCode());
        result.addMessage(err.getMessage());
        result.setToast(err.getToast());
        return result;
    }
}
