package unicorn.mp.common.utils;

import com.alibaba.fastjson.JSON;
import unicorn.mp.common.enumation.ResponseError;
import unicorn.mp.common.enumation.ResponseUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class ErrorResponseGenerator {

    public static void generateErrorMessage(ResponseError responseError, HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setContentType("text/html; charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();

        ResultWrapper<?> result = ResponseUtil.error(responseError);

        out.write(JSON.toJSONString(result));
        out.flush();
        out.close();
    }

    public static void generateErrorMessage(String code, String message, String toast, HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setContentType("text/html; charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();

        ResultWrapper<?> result = ResponseUtil.error(code, message, toast);

        out.write(JSON.toJSONString(result));
        out.flush();
        out.close();
    }

    public static void generateErrorMessage(ResultWrapper<?> result, HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setContentType("text/html; charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();

        out.write(JSON.toJSONString(result));
        out.flush();
        out.close();
    }

}
