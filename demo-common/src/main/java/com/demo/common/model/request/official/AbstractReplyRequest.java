package com.demo.common.model.request.official;

import com.demo.common.model.bo.BaseDO;
import lombok.Getter;
import lombok.Setter;

/**
 * 公众帐号 -> 普通用户
 */
@Getter
@Setter
public abstract class AbstractReplyRequest extends BaseDO {

    private static final long serialVersionUID = -6244277633057415731L;
    /**
     *  接收方 开发者微信号
     */
    private String ToUserName;
    /**
     *  发送方帐号
     */
    private String FromUserName;
    /**
     *  消息创建时间 （整型）
     */
    private long CreateTime;
    /**
     *  消息类型 例如 /text/image 回复
     */
    private String MsgType = SetMsgType();
    /**
     * 消息类型
     *
     * @return
     */
    public abstract String SetMsgType();

}

