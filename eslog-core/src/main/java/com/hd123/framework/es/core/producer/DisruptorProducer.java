package com.hd123.framework.es.core.producer;

import com.hd123.framework.es.core.communication.disruptor.DisruptorHandler;
import com.hd123.framework.es.core.message.Message;

/**
 * Created by LIWEIHUA on 2018-03-23.
 * 队列推送,生产者用单例bean来处理
 */


public class DisruptorProducer implements Producer{


    public DisruptorProducer() {
    }

    public void send(Message message) {
        DisruptorHandler.getInsatance().publish(message);
    }

}
