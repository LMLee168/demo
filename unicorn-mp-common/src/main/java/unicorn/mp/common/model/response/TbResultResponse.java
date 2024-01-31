package unicorn.mp.common.model.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TbResultResponse {

    @JSONField(name = "request_id")
    private String requestId;
    @JSONField(name = "error_response")
    private String errorResponse;
    private String code;
    private String msg;
    @JSONField(name = "sub_code")
    private String subCode;
    @JSONField(name = "sub_msg")
    private String subMsg;
}
