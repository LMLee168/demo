package com.demo;

import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

public class TestListener implements TestExecutionListener {

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        //设置环境变量 解决es冲突
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }

}