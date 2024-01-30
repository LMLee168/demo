package unicorn.mp.api.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import unicorn.mp.api.interceptor.JsonParamArgumentResolver;
import unicorn.mp.api.interceptor.LoginInterceptor;
import com.demo.common.provacy.PrivacyFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.cbor.MappingJackson2CborHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.smile.MappingJackson2SmileHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //metrics追踪reqId
//        registry.addInterceptor(metricsTraceInterceptor())
//                .excludePathPatterns("/doc.html","/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");


        // 登录校验
        registry.addInterceptor(loginInterceptor()).excludePathPatterns("/doc.html", "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");

//        //metrics追踪id
//        registry.addInterceptor(metricsUserInterceptor()).excludePathPatterns("/doc.html", "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(jsonParamArgumentResolver());
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        Iterator<HttpMessageConverter<?>> iterator = converters.iterator();
        while (iterator.hasNext()) {
            HttpMessageConverter<?> converter = iterator.next();
            if (converter instanceof MappingJackson2HttpMessageConverter
                    || converter instanceof MappingJackson2SmileHttpMessageConverter
                    || converter instanceof MappingJackson2CborHttpMessageConverter) {
                iterator.remove();
            }
        }
        // 1.需要先定义一个convert 转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        // 2.添加fastJson的配置信息,比如，是否需要格式化返回的json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
                SerializerFeature.QuoteFieldNames,
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.DisableCircularReferenceDetect);
        // 添加数据脱敏filter
        fastJsonConfig.setSerializeFilters(new PrivacyFilter());
        // 处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        // 3.在convert中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);
        // 4.将convert添加到converters当中
        converters.add(fastConverter);
    }

    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

    public JsonParamArgumentResolver jsonParamArgumentResolver() {
        return new JsonParamArgumentResolver();
    }

}
