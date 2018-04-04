package com.hd123.framework.es.core.producer;

import com.hd123.framework.es.core.message.Message;

/**
 * Created by LIWEIHUA on 2018-03-23.
 *
 */

/**
 * 生产者与消费者使用线程池，不能使用单例，否则容易发生瓶颈
 * 至于放哪个实例，由配置决定
 */
public interface Producer {

    /**
     * 发送消息
     */
    void send(Message message);
}
