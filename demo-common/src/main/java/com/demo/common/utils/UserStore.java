package com.demo.common.utils;

import com.demo.common.model.bo.DeviceInfoBO;

public class UserStore {
    private static final UserStore instance = new UserStore();
    private final ThreadLocal<Long> threadLocal = new ThreadLocal();
    private final ThreadLocal<DeviceInfoBO> deviceThreadLocal = new ThreadLocal();
    private final ThreadLocal<String> userIpThreadLocal = new ThreadLocal();

    private UserStore() {
    }

    public static UserStore getInstance() {
        return instance;
    }

    public void set(Long userId) {
        this.threadLocal.set(userId);
    }

    public Long get() {
        return (Long)this.threadLocal.get();
    }

    public void remove() {
        this.threadLocal.remove();
        this.deviceThreadLocal.remove();
        this.userIpThreadLocal.remove();
    }

    public void setDevice(DeviceInfoBO bo) {
        this.deviceThreadLocal.set(bo);
    }

    public void setUserIp(String ip) {
        this.userIpThreadLocal.set(ip);
    }

    public String getUserIp() {
        return (String)this.userIpThreadLocal.get();
    }

    public DeviceInfoBO getDevice() {
        return (DeviceInfoBO)this.deviceThreadLocal.get();
    }
}

