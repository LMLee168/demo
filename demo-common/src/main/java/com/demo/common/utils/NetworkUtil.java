package com.demo.common.utils;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;



import java.net.InetAddress;
import java.util.Map;

public class NetworkUtil {


        private static Logger logger = LoggerFactory.getLogger(NetworkUtil.class);

        public static String getIpAddr(HttpServletRequest request) {
            String ip = request.getHeader("x-forwarded-for");
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
                if(ip.equals("127.0.0.1")){
                    //根据网卡取本机配置的IP
                    InetAddress inet=null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    ip= inet.getHostAddress();
                }
            }
            // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if(ip != null && ip.length() > 15){
                if(ip.indexOf(",")>0){
                    ip = ip.substring(0,ip.indexOf(","));
                }
            }
            return ip;
        }

        public static String getPlatForm(HttpServletRequest request) {
            Map<String, String[]> params = request.getParameterMap();
            logger.info("soda params is {}", JSON.toJSONString(params));
            String platform = "unknown";
            String[] platforms = params.get("platform");

            if (platforms != null && platforms.length > 0) {

                logger.info("platforms is {}", JSON.toJSONString(platforms));
                platform = platforms[0];

                if ("ios".equals(platform)) {
                    return "iOS";
                }
            }
            return platform;
        }

        public static String getAppVer(HttpServletRequest request) {
            Map<String, String[]> params = request.getParameterMap();
            for(Map.Entry<String, String[]> entry : params.entrySet()){
                if(entry.getValue() == null){
                    logger.info("param key={}, value is null", entry.getKey());
                }else{
                    StringBuilder sb = new StringBuilder();
                    for(String val : entry.getValue()){
                        sb.append(sb).append(", ");
                    }
                    if(sb.length() > 20){
                        logger.info("param key={}, value too long", entry.getKey());
                    }else{
                        logger.info("param key={}, value={}", entry.getKey(), entry.getValue());
                    }

                }
            }

            String app_ver = "unknown";
            String[] app_vers = params.get("app_ver");

            if (app_vers != null && app_vers.length > 0) {
                app_ver = app_vers[0];
            }

            logger.info("get appver is {}", app_ver);
            return app_ver;
        }

        public static int compareAppver(String appVer1, String appVer2){
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


        public static void main(String []args){
            System.out.println(compareAppver("0.8.7", "0.8.8"));
            System.out.println(compareAppver("0.9.7", "0.8.8"));
            System.out.println(compareAppver("0.10", "0.8.8"));
            System.out.println(compareAppver("1", "0.8.8"));
            System.out.println(compareAppver("0.7.12", "0.8.8"));
            System.out.println(compareAppver("0.9.3", "0.8.8"));
        }

    }
