package com.hd123.framework.es.core.common;


import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by LIWEIHUA on 2018-03-27.
 * 线程池属性
 */
public class ThreadMessageFactory implements ThreadFactory{

    private String threadNamePrefix;
    private AtomicInteger threadIndex= new AtomicInteger(0);


    public ThreadMessageFactory(String threadNamePrefix) {
        this.threadNamePrefix = threadNamePrefix;
    }


    public Thread newThread(Runnable r) {
        return new Thread(r, threadNamePrefix + this.threadIndex.incrementAndGet());
    }
}
