package com.demo.common.model.response.tb;

import com.demo.common.model.response.TbResultResponse;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WirelessShareTpwdQueryResponse extends TbResultResponse {
    /**
     * 淘口令-文案
     */
    private String content;
    /**
     * 淘口令-标题
     */
    private String title;
    /**
     * 如果是宝贝，则为宝贝价格
     */
    private String price;
    /**
     * 图片url
     */
    private String pic_url;
    /**
     * 是否成功
     */
    private Boolean suc;
    /**
     *  nativeUrl
     */
    private String native_url;
    /**
     * thumbPicUrl
     */
    private String thumb_pic_url;

}
