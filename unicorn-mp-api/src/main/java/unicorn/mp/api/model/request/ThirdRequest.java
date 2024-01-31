package unicorn.mp.api.model.request;

import unicorn.mp.common.model.bo.BaseDO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThirdRequest extends BaseDO {

    private String accessToken;
}
