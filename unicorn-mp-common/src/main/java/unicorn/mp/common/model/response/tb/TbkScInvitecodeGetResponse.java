package unicorn.mp.common.model.response.tb;

import com.alibaba.fastjson.annotation.JSONField;
import unicorn.mp.common.model.response.TbResultResponse;
import lombok.Data;

@Data
public class TbkScInvitecodeGetResponse extends TbResultResponse {

    @JSONField(name = "inviter_code")
    private String inviterCode;
}
