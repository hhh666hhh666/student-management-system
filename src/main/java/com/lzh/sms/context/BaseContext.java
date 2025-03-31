package com.lzh.sms.context;

/**
 * 基于ThreadLocal封装的工具类，用于保存和获取当前用户ID
 */
public class BaseContext {

    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /**
     * 设置当前用户ID
     */
    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    /**
     * 获取当前用户ID
     */
    public static Long getCurrentId() {
        return threadLocal.get();
    }

    /**
     * 清除当前用户ID
     */
    public static void removeCurrentId() {
        threadLocal.remove();
    }
}
