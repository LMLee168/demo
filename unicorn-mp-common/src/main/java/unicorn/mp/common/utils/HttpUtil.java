package unicorn.mp.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtil {

    private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);
    private static RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(5000).setConnectTimeout(5000).setSocketTimeout(5000).build();
    public HttpUtil() {
    }

    public static String doGet(String url, Map<String, Object> params) throws IOException, URISyntaxException {
        String result = null;
        CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
        URIBuilder uriBuilder = new URIBuilder(url);
        if (!CollectionUtils.isEmpty(params)) {
            params.forEach((k,v) -> uriBuilder.setParameter(k,v != null ? v.toString() : null));
        }

        URI uri = uriBuilder.build();
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpResponse response = null;

        try {
            response = httpclient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.OK.value()) {
                result = EntityUtils.toString(response.getEntity(), "UTF-8");
            }else {
                log.error("http get request status code error, statusCode = {}", response.getStatusLine().getStatusCode());
            }
        } catch (Exception var12) {
            log.error(var12.getMessage(), var12);
            throw var12;
        } finally {
            if (response != null) {
                response.close();
            }

            httpclient.close();
        }

        return result;
    }

    public static String doGetJson(String url, Map<String, Object> params) throws IOException, URISyntaxException {
        String result = null;
        CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();

        URIBuilder uriBuilder = new URIBuilder(url);
        if (!CollectionUtils.isEmpty(params)) {
            params.forEach((k,v) -> uriBuilder.setParameter(k,v != null ? v.toString() : null));
        }
        URI uri = uriBuilder.build();
        HttpGet httpGet = new HttpGet(uri);
        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Content-type", "application/json");

        CloseableHttpResponse response = null;

        try {
            response = httpclient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.OK.value()) {
                result = EntityUtils.toString(response.getEntity(), "UTF-8");
            }else {
                log.error("http get request status code error, statusCode = {}", response.getStatusLine().getStatusCode());
            }
        } catch (Exception var12) {
            log.error(var12.getMessage(), var12);
            throw var12;
        } finally {
            if (response != null) {
                response.close();
            }

            httpclient.close();
        }

        return result;
    }

    public static String doPost(String url, Map<String, Object> params) throws IOException {
        return doPost(url, params, null);
    }

    public static String doPost(String url, Map<String, Object> params, Charset charset) throws IOException {
        String result = null;
        CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> parameters = new ArrayList(0);
        params.forEach((key, value) -> {
            parameters.add(new BasicNameValuePair(key, value != null ? value.toString() : null));
        });
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters, charset);
        httpPost.setEntity(formEntity);
        CloseableHttpResponse response = null;

        try {
            response = httpclient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.OK.value()) {
                result = EntityUtils.toString(response.getEntity(), "UTF-8");
            }else {
                log.error("http post request status code error, statusCode = {}", response.getStatusLine().getStatusCode());
            }
        } catch (Exception var13) {
            log.error(var13.getMessage(), var13);
        } finally {
            if (response != null) {
                response.close();
            }

            httpclient.close();
        }

        return result;
    }

    public static String doPostJson(String url, String jsonParams) throws IOException {
        String result = null;

        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        // 创建http POST请求
        HttpPost httpPost = new HttpPost(url);

        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        StringEntity entity = new StringEntity(jsonParams, "UTF-8");
        httpPost.setEntity(entity);

        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpclient.execute(httpPost);

            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == HttpStatus.OK.value()) {
                result = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (response != null) {
                response.close();
            }
            httpclient.close();
        }

        return result;
    }


    public static String doPostJson(String url, Map<String, Object> params) throws IOException {
        String result = null;
        CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        StringEntity entity = new StringEntity(JSONObject.toJSONString(params), "UTF-8");
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;

        try {
            response = httpclient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.OK.value()) {
                result = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception var11) {
            log.error(var11.getMessage(), var11);
        } finally {
            if (response != null) {
                response.close();
            }

            httpclient.close();
        }

        return result;
    }

    public static String doPostFile(String url, MultipartFile multipartFile, String fileParamName) {

        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
        String result = "";
        //每个post参数之间的分隔。随意设定，只要不会和其他的字符串重复即可。
        String boundary ="----WebKitFormBoundary0j3qBlYRoC96tFB3";
        try {

            HttpPost httpPost = new HttpPost(url);
            //设置请求头
            httpPost.setHeader("Content-Type","multipart/form-data; boundary="+boundary);
            //HttpEntity builder
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            //字符编码
            builder.setCharset(Charset.forName("UTF-8"));
            //模拟浏览器
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            //boundary
            builder.setBoundary(boundary);
            //方式1： multipart/form-data
            //文件名
            String fileName = multipartFile.getOriginalFilename();
            File file = new File(fileName);
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
            builder.addPart(fileParamName,new FileBody(file));

            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            // 执行提交
            HttpResponse response = httpClient.execute(httpPost);
            //响应
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                // 将响应内容转换为字符串
                result = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
            }
        } catch (Exception e) {
            log.info("e:{}", e);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.err.println("result"+result);
        return result;
    }

}
