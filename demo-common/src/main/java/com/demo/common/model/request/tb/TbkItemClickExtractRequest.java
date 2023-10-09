package com.demo.common.model.request.tb;

import com.demo.common.model.request.SavvyBuyRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TbkItemClickExtractRequest extends SavvyBuyRequest {
    /**
     * https://s.click.taobao.com/***	长链接或短链接
     */
    private String click_url;
}
