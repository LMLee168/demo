package com.demo.common.model.response.jd;

import com.demo.common.model.response.PromotionAgg;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ZheTaoKeAggPromotionGetResponse extends JdResultResponse{
    private Integer status;
    private List<PromotionAgg> content;


}
