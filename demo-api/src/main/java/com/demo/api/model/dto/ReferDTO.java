package com.demo.api.model.dto;

import com.demo.common.model.bo.BaseDO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReferDTO extends BaseDO {

    /**
     * 原始数据来源，或数据源说明，可能为空
     */
    private List<String> sources;

    /**
     * 数据许可或版权声明，可能为空
     */
    private List<String> license;
}
