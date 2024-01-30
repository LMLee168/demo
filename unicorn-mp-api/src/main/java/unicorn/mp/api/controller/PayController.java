package unicorn.mp.api.controller;

import com.demo.common.enumation.ResponseUtil;
import com.demo.common.utils.ResultWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/demo/v1/pay")
public class PayController {

    /**
     * 支付二维码
     * @return
     */
    @GetMapping("/code")
    public ResultWrapper<?> payCode() {


        return ResponseUtil.success(null);
    }

    @GetMapping("/trade")
    public ResultWrapper<?> trade() {


        return ResponseUtil.success(null);
    }


    @GetMapping("/callback")
    public ResultWrapper<?> callback(HttpServletRequest request, HttpServletResponse response) {


        return ResponseUtil.success(null);
    }
}
