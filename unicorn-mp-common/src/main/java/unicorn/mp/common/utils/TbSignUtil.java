package unicorn.mp.common.utils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static tk.mybatis.mapper.util.StringUtil.isNotEmpty;

@Slf4j
public class TbSignUtil {
    private static final String SIGN_METHOD_MD5 = "md5";
    private static final String SIGN_METHOD_HMAC = "hmac";
    private static final String CHARSET_UTF8 = "utf-8";
    private static final String CONTENT_ENCODING_GZIP = "gzip";

    // TOP服务地址，正式环境需要设置为http://gw.api.taobao.com/router/rest
    private static final String serverUrl = "http://gw.api.taobao.com/router/rest";
    private static final String tbAppKey = "34396407"; // 可替换为您的应用的appKey
    private static final String tbSec = "23843925ae2477cd2918102367d2c2eb"; // 可替换为您的应用的appSecret
    private static final String sessionKey = ""; // 必须替换为授权得到的真实有效sessionKey

    //jd
    private static final String jdApKEY ="c70a395a3bbd43d81ac620b414a800f9";
    private static final String jd_sec = "cd2c2c8d1bb54bacb743c430a58e8f09";

    //dy
    private static final String dyApKEY ="c70a395a3bbd43d81ac620b414a800f9";
    private static final String dy_sec = "cd2c2c8d1bb54bacb743c430a58e8f09";

    /**
     * 对TOP请求进行签名。
     */
    private static String signTopRequest(Map<String, Object> params, String secret, String signMethod) throws IOException {
        // 第一步：检查参数是否已经排序
        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);

        // 第二步：把所有参数名和参数值串在一起
        StringBuilder query = new StringBuilder();
        if (SIGN_METHOD_MD5.equals(signMethod)) {
            query.append(secret);
        }
        for (String key : keys) {
            Object value = params.get(key);
            if (isNotEmpty(key)) {
                query.append(key).append(value);
            }
        }

        // 第三步：使用MD5/HMAC加密
        byte[] bytes;
        if (SIGN_METHOD_HMAC.equals(signMethod)) {
            bytes = encryptHMAC(query.toString(), secret);
        } else {
            query.append(secret);
            bytes = encryptMD5(query.toString());
        }

        // 第四步：把二进制转化为大写的十六进制
        return byte2hex(bytes);
    }

    /**
     * 对字节流进行HMAC_MD5摘要。
     */
    private static byte[] encryptHMAC(String data, String secret) throws IOException {
        byte[] bytes = null;
        try {
            SecretKey secretKey = new SecretKeySpec(secret.getBytes(CHARSET_UTF8), "HmacMD5");
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            bytes = mac.doFinal(data.getBytes(CHARSET_UTF8));
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse.toString());
        }
        return bytes;
    }

    /**
     * 对字符串采用UTF-8编码后，用MD5进行摘要。
     */
    private static byte[] encryptMD5(String data) throws IOException {
        return encryptMD5(data.getBytes(CHARSET_UTF8));
    }

    /**
     * 对字节流进行MD5摘要。
     */
    private static byte[] encryptMD5(byte[] data) throws IOException {
        byte[] bytes = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            bytes = md.digest(data);
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse.toString());
        }
        return bytes;
    }

    /**
     * 把字节流转换为十六进制表示方式。
     */
    private static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }



    public static void main(String[] args) throws Exception {
//        System.out.println(getCommonItem();
    }


    public static Map<String, Object> getTbCommonItem(Map<String, Object> params){
        // 公共参数
        params.put("app_key", tbAppKey);
        params.put("session", sessionKey);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        params.put("timestamp", df.format(new Date()));
        params.put("format", "json");
        params.put("v", "2.0");
        params.put("sign_method", SIGN_METHOD_HMAC);
        // 签名参数
        try {
            params.put("sign", signTopRequest(params, tbSec, SIGN_METHOD_HMAC));
        } catch (IOException e) {
            throw new RuntimeException("获取签名失败，稍后再试");
        }
        // 请用API
        return params;
    }
    public static Map<String, Object> getJdCommonItem(Map<String, Object> queryParams, String apiMethod){

        Map<String, Object> params = Maps.newHashMap();
        params.put("access_token", sessionKey);
        params.put("app_key", jdApKEY);
        params.put("method",apiMethod);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        params.put("timestamp", df.format(new Date()));
        params.put("sign_method", SIGN_METHOD_MD5);
        params.put("v", "1.0");
        params.put("360buy_param_json",JSON.toJSONString(queryParams));

        log.info("参数 {}", JSON.toJSONString(params));
        // 签名参数
        try {
            params.put("sign", jdSign(params));
        } catch (Exception e) {
            throw new RuntimeException("获取签名失败，稍后再试");
        }
        // 请用API
        return params;
    }
    private static String jdSign(Map<String, Object> params) {
        List<String> sortKey = Lists.newArrayList("360buy_param_json", "access_token", "app_key", "method", "sign_method", "timestamp","v");
        StringBuilder builder = new StringBuilder(jd_sec);
        for (String key : sortKey) {
            Object value = params.get(key);
            if (value  == null) {
                continue;
            }
            builder.append(key).append(value);
        }
        builder.append(jd_sec);
        return DigestUtils.md5Hex(builder.toString()).toUpperCase();
    }


    public static Map<String, Object> getDyCommonItem(Map<String, Object> queryParams, String apiMethod){

        Map<String, Object> params = Maps.newHashMap();
        params.put("access_token", sessionKey);
        params.put("app_key", dyApKEY);
        params.put("method",apiMethod);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        params.put("timestamp", df.format(new Date()));
        params.put("sign_method", SIGN_METHOD_MD5);
        params.put("v", "1.0");
        params.put("param_json",JSON.toJSONString(queryParams));

        log.info("参数 {}", JSON.toJSONString(params));
        // 签名参数
        try {
            params.put("sign", jdSign(params));
        } catch (Exception e) {
            throw new RuntimeException("获取签名失败，稍后再试");
        }
        // 请用API
        return params;
    }
}
