package com.demo.common.model.response.jd;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryHttpResponse{

    private UnionOpenGoodsPromotiongoodsinfoQueryResponse jd_union_open_goods_promotiongoodsinfo_query_responce;
    private ErrorRessponse error_response;

    private UnionOpenPromotionBysubunionidGetResponse jd_union_open_promotion_bysubunionid_get_response;
}
