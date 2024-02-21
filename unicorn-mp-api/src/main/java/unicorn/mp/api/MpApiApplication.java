package unicorn.mp.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableFeignClients(basePackages = {"com.wanmei.auth","com.wanmei.chengjia.client","com.wanmei.social"}) // 跨域问题

//@EnableRocketMQ  //mq
@SpringBootApplication(scanBasePackages = {"unicorn.mp.common.manager","unicorn.mp.common.cache"})
public class MpApiApplication {

    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        System.setProperty("io.netty.allocator.type", "unpooled");
        SpringApplication.run(MpApiApplication.class, args);
    }
}
