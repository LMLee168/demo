package unicorn.mp.common.model.response.jd;

import unicorn.mp.common.model.bo.BaseDO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorRessponse extends BaseDO {

    private Integer code;
    private String zh_desc;
}
