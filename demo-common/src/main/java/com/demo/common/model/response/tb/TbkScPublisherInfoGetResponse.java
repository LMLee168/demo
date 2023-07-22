package com.demo.common.model.response.tb;

import com.alibaba.fastjson.annotation.JSONField;
import com.demo.common.model.response.TbResultResponse;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TbkScPublisherInfoGetResponse extends TbResultResponse {

    @JSONField(name = "total_count")
    private Integer totalCount;
    /**
     * 共享字段 - 渠道或会员列表
     */
    @JSONField(name = "inviter_list")
    private List<Inviter> inviterList;
    /**
     * 渠道专属pidList  ["mm_1_1_1"]
     */
    @JSONField(name = "root_pid_channel_list")
    private List<String> rootPidChannelList;

    @Data
    public class Inviter{
        /**
         * 共享字段 - 备案场景：common（通用备案），etao（一淘备案），minietao（一淘小程序备案），offlineShop（线下门店备案），offlinePerson（线下个人备案）
         */
        @JSONField(name = "relation_app")
        private String relationApp;
        /**
         * 共享字段 - 备案日期
         */
        @JSONField(name = "create_date")
        private Date createDate;
        /**
         * 渠道独有 - 渠道昵称
         */
        @JSONField(name = "account_name")
        private String accountName;
        /**
         * 渠道独有 - 渠道名称
         */
        @JSONField(name = "real_name")
        private String realName;
        /**
         * 渠道独有 - 渠道关系ID
         */
        @JSONField(name = "relation_id")
        private Integer relationId;
        /**
         * 渠道独有 - 线下场景信息，1 - 门店，2- 学校，3 - 工厂，4 - 其他
         */
        @JSONField(name = "offline_scene")
        private String offlineScene;
        /**
         * 渠道独有 - 线上场景信息，1 - 微信群，2- QQ群，3 - 其他
         */
        @JSONField(name = "online_scene")
        private String onlineScene;
        /**
         * 渠道独有 - 媒体侧渠道备注信息
         */
        private String note;
        /**
         * 共享字段 - 渠道/会员专属pid
         */
        @JSONField(name = "root_pid")
        private String rootPid;
        /**
         * 共享字段 - 渠道/会员原始身份信息
         */
        private String rtag;
        /**
         * 线下备案专属信息
         */
        @JSONField(name = "offline_info")
        private RegisterInfoDto offlineInfo;
        @JSONField(name = "special_id")
        private Integer specialId;
        /**
         * 渠道独有 - 处罚状态
         */
        @JSONField(name = "punish_status")
        private String punishStatus;
        /**
         * 淘宝客外部用户标记
         */
        @JSONField(name = "external_id")
        private String externalId;
        /**
         * 1-微信、2-微博、3-抖音、4-快手、5-QQ，0-其他
         */
        @JSONField(name = "external_type")
        private String externalType;
    }

    @Data
    public class RegisterInfoDto{
        /**
         * 渠道独有 -店铺名称
         */
        @JSONField(name = "shop_name")
        private String shopName;
        /**
         * 渠道独有 -店铺类型  生活服务类 电信营业厅
         */
        @JSONField(name = "shop_type")
        private String shopType;
        /**
         * 渠道独有 - 信息
         */
        @JSONField(name = "phone_number")
        private String phoneNumber;
        @JSONField(name = "detail_address")
        private String detailAddress;
        private String location;
        /**
         * 类型  营业执照
         */
        @JSONField(name = "shop_certify_type")
        private String shopCertifyType;
        /**
         *  编号
         */
        @JSONField(name = "certify_number")
        private String certifyNumber;
        /**
         * 经营类型 个人 快递员
         */
        private String career;

    }
}
