package unicorn.mp.api.model.request;

import com.demo.common.model.bo.BaseDO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThirdRequest extends BaseDO {

    private String accessToken;
}
