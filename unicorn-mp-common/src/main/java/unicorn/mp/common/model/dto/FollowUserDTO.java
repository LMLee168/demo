package unicorn.mp.common.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class FollowUserDTO {
    private String userid;
    private String remark;
    private String description;
    private Long createtime;
    private List<TagsDTO> tags;
    private List<String> remark_mobiles;
    private Integer add_way;
    private String oper_userid;
}
