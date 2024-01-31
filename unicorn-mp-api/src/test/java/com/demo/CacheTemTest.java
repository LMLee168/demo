package com.demo;

import unicorn.mp.common.cache.CacheTemplate;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

public class CacheTemTest extends BaseTest{

    @Resource
    private CacheTemplate cacheTemplate;

    @Test
    public void cach(){
        cacheTemplate.setValue("mmm", "1", 1, TimeUnit.DAYS);
    }
}
