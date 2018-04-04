package com.hd123.framework.es.core.consumer;

import com.hd123.framework.es.core.storage.Storage;

/**
 * Created by LIWEIHUA on 2018-03-23.
 */

/**
 * 消费者使用线程池，不能使用单例，否则容易发生瓶颈
 */
public abstract class Consumer {


    private Storage abstractStorage;

    public Storage getAbstractStorage() {
        return abstractStorage;
    }

    public void setAbstractStorage(Storage abstractStorage) {
        this.abstractStorage = abstractStorage;
    }




    /**
     * 获取消息
     */
    //Message fetch();


}
