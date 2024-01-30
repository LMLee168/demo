package unicorn.mp.common.config;

import org.slf4j.MDC;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class MdcThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {
    private static final long serialVersionUID = 6020796436530651122L;

    public MdcThreadPoolTaskExecutor() {
    }

    public void execute(Runnable task) {
        Map<String, String> contextMap = MDC.getCopyOfContextMap();
        super.execute(() -> {
            MDC.setContextMap(contextMap);

            try {
                task.run();
            } finally {
                MDC.clear();
            }

        });
    }

    public <T> Future<T> submit(Callable<T> task) {
        Map<String, String> contextMap = MDC.getCopyOfContextMap();
        return super.submit(new MdcAwareCallable(task, contextMap));
    }

    public Future<?> submit(Runnable task) {
        Map<String, String> contextMap = MDC.getCopyOfContextMap();
        return super.submit(() -> {
            MDC.setContextMap(contextMap);

            try {
                task.run();
            } finally {
                MDC.clear();
            }

        });
    }
}

