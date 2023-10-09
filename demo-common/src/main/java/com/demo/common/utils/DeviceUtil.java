package com.demo.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * 设备
 */
public abstract class DeviceUtil {

    public static int compareAppVer(String appVer1, String appVer2){
        if(StringUtils.isBlank(appVer1) && StringUtils.isBlank(appVer2)){
            return 0;
        }else if(StringUtils.isBlank(appVer1)){
            return -1;
        }else if(StringUtils.isBlank(appVer2)){
            return 1;
        }
        String[] appVer1Arr = StringUtils.split(appVer1, ".");
        String[] appVer2Arr = StringUtils.split(appVer2, ".");
        int len = Math.min(appVer1Arr.length, appVer2Arr.length);
        for(int index = 0; index < len; index++){
            int number1 = NumberUtils.toInt(appVer1Arr[index]);
            int number2 = NumberUtils.toInt(appVer2Arr[index]);
            int cmpVal = number1 - number2;
            if(cmpVal != 0){
                return cmpVal > 0 ? 1 : -1;
            }
        }
        if(appVer1Arr.length == appVer2Arr.length){
            return 0;
        }
        return appVer1Arr.length > appVer2Arr.length ? 1 : -1;
    }

}
