package unicorn.mp.common.enumation;

import lombok.Getter;

@Getter
public enum OfficialMsgTypeEnum {

    TEXT(1, "text"),
    ;

    private final Integer value;
    private final String type;

    OfficialMsgTypeEnum(Integer value, String type) {
        this.type = type;
        this.value = value;
    }
}
