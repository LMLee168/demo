package unicorn.mp.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableFeignClients(basePackages = {"com.wanmei.auth","com.wanmei.chengjia.client","com.wanmei.social"}) // 跨域问题

//@EnableRocketMQ  //mq
@SpringBootApplication(scanBasePackages = {"com.demo.common.manager","com.demo.common.cache"})
public class DemoApplication {

    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        System.setProperty("io.netty.allocator.type", "unpooled");
        SpringApplication.run(DemoApplication.class, args);
    }
}
