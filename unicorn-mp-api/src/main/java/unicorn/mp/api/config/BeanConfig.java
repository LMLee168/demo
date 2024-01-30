package unicorn.mp.api.config;

import com.demo.common.cache.CacheTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean(name = "cacheTemplate")
    public CacheTemplate createCacheTemplate() {
        return new CacheTemplate();
    }

}