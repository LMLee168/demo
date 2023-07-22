package com.demo.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoScheduleApplication {

    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        System.setProperty("io.netty.allocator.type", "unpooled");
        SpringApplication.run(DemoScheduleApplication.class, args);
    }
}
