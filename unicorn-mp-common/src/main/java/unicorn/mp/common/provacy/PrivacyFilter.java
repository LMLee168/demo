package unicorn.mp.common.provacy;

import com.alibaba.fastjson.serializer.ValueFilter;
import unicorn.mp.common.encrypt.PrivacyEncrypt;
import unicorn.mp.common.enumation.PrivacyTypeEnum;
import unicorn.mp.common.utils.PrivacyUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class PrivacyFilter implements ValueFilter {
    private static final Logger log = LoggerFactory.getLogger(PrivacyFilter.class);

    public PrivacyFilter() {
    }

    public Object process(Object object, String name, Object value) {
        try {
            if (!(value instanceof String)) {
                return value;
            }

            Field[] fields = object.getClass().getDeclaredFields();
            Field[] var5 = fields;
            int var6 = fields.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                Field field = var5[var7];
                if (field.getName().equals(name)) {
                    PrivacyEncrypt privacyEncrypt = (PrivacyEncrypt)field.getAnnotation(PrivacyEncrypt.class);
                    if (privacyEncrypt == null) {
                        return value;
                    }

                    PrivacyTypeEnum type = privacyEncrypt.type();
                    int prefixNoMaskLen = privacyEncrypt.prefixNoMaskLen();
                    int suffixNoMaskLen = privacyEncrypt.suffixNoMaskLen();
                    String symbol = privacyEncrypt.symbol();
                    return this.serialize((String)value, type, prefixNoMaskLen, suffixNoMaskLen, symbol);
                }
            }
        } catch (Exception var14) {
            log.error(var14.getMessage(), var14);
        }

        return value;
    }

    public Object serialize(String origin, PrivacyTypeEnum privacyTypeEnum, int prefixNoMaskLen, int suffixNoMaskLen, String symbol) {
        if (StringUtils.isNotBlank(origin) && null != privacyTypeEnum) {
            switch (privacyTypeEnum) {
                case CUSTOMER:
                    return PrivacyUtil.desValue(origin, prefixNoMaskLen, suffixNoMaskLen, symbol);
                case NAME:
                    return PrivacyUtil.hideChineseName(origin);
                case ID_CARD:
                    return PrivacyUtil.hideIDCard(origin);
                case PHONE:
                    return PrivacyUtil.hidePhone(origin);
                case EMAIL:
                    return PrivacyUtil.hideEmail(origin);
                default:
                    throw new IllegalArgumentException("unknown privacy type enum " + privacyTypeEnum);
            }
        } else {
            return origin;
        }
    }
}
