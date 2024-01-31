package unicorn.mp.common.model.dto;

import unicorn.mp.common.model.bo.BaseDO;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class WorkWxCallbackDTO extends BaseDO {
    private static final long serialVersionUID = 5854952828767113614L;
    private Map<String,Object> xmlRequestParams;

    private Long userId;

    private ExtContactDTO extContact;

    private List<FollowUserDTO> followUsers;
}
