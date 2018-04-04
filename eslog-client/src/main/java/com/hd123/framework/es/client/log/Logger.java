package com.hd123.framework.es.client.log;

/**
 * Created by LIWEIHUA on 2018-03-23.
 * 提供给对外的日志记录接口
 */
public interface Logger {

    /**
     * info日志
     * @param msg
     */
    void info(String msg);

    /**
     * error日志
     * @param msg
     */
    void error(String msg);

    /**
     * debug日志
     * @param msg
     */
    void debug(String msg);

    /**
     *
     * @param msg
     * @param level
     */
    void write(String msg, Integer level);

    /**
     * 写http请求日志
     * @param request
     * @param response
     */
    void write(String request, String response);
}
