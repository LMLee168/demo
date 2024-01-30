package unicorn.mp.common.model.response.dy;

import unicorn.mp.common.model.bo.BaseDO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DyResultResponse extends BaseDO {

    private Integer code;
    private String msg;
    private String sub_code;
    private String sub_msg;
}
