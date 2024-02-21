package unicorn.mp.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MpScheduleApplication {

    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        System.setProperty("io.netty.allocator.type", "unpooled");
        SpringApplication.run(MpScheduleApplication.class, args);
    }
}
