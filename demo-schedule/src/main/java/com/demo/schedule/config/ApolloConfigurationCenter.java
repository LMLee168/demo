package com.demo.schedule.config;

import com.alibaba.fastjson.JSON;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@EnableApolloConfig
@Slf4j
@Getter
public class ApolloConfigurationCenter {

    @ApolloConfig
    private Config config;

    @Value("${whiteListIpConfig:[]}")
    private String whiteListIpConfig;
    private List<String> whiteListIp;

    @PostConstruct
    public void init() {
        whiteListIp = JSON.parseArray(whiteListIpConfig, String.class);
    }


    @ApolloConfigChangeListener
    private void onChange(ConfigChangeEvent changeEvent) {
        try {
            if (changeEvent.isChanged("whiteListIpConfig")) {
                this.whiteListIpConfig = config.getProperty("whiteListIpConfig", "[]");
                log.info("Apollo config update. key:whiteListIpConfig ==> newValue:{}", whiteListIpConfig);
                whiteListIp = JSON.parseArray(whiteListIpConfig, String.class);
            }
        } catch (Exception e) {
            log.error("update apollo value error", e);
            throw e;
        }
    }


}
