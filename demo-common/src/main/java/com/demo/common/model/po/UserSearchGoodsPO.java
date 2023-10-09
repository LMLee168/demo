package com.demo.common.model.po;

import com.demo.common.model.bo.BaseDO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Table(name = "user_search_goods")
public class UserSearchGoodsPO extends BaseDO {
    private static final long serialVersionUID = -4469114429332852210L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "open_id")
    private String openId;

    /**
     * SavvyBuyChannelEnum
     */
    private Integer channel;

    @Column(name = "googs_id")
    private String goodsId;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

}
