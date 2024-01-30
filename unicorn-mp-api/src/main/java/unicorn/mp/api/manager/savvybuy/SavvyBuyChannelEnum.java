package unicorn.mp.api.manager.savvybuy;

import lombok.Getter;

@Getter
public enum SavvyBuyChannelEnum {
    JD("m.jd.com", "jd"),
    TB("m.tb.com", "tb"),
    PDD("mobile.yangkeduo.com", "pdd"),
    ;
    private final String domain;
    private final String channel;

    SavvyBuyChannelEnum(String domain, String channel) {
        this.domain = domain;
        this.channel = channel;
    }

    public static SavvyBuyChannelEnum getByDomain(String domain) {
        for (SavvyBuyChannelEnum typeEnum : values()) {
            if (typeEnum.domain.equals(domain)) {
                return typeEnum;
            }
        }
        return null;
    }
}
