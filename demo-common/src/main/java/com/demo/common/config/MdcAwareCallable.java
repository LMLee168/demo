package com.demo.common.config;

import org.slf4j.MDC;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class MdcAwareCallable<T> implements Callable<T> {
    private final Callable<T> delegate;
    private final Map<String, String> contextMap;

    public MdcAwareCallable(Callable<T> callable, Map<String, String> contextMap) {
        this.delegate = callable;
        this.contextMap = (Map)(contextMap != null ? contextMap : new HashMap());
    }

    @Override
    public T call() throws Exception {
        Object var1;
        try {
            MDC.setContextMap(this.contextMap);
            var1 = this.delegate.call();
        } finally {
            MDC.clear();
        }

        return (T) var1;
    }
}

