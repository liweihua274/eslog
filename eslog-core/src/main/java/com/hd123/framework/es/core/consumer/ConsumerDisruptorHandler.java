package com.hd123.framework.es.core.consumer;

import com.hd123.framework.es.core.common.ThreadManager;
import com.hd123.framework.es.core.message.Message;
import com.lmax.disruptor.EventHandler;

/**
 * Created by LIWEIHUA on 2018-03-28.
 */
public class ConsumerDisruptorHandler implements EventHandler {
    public void onEvent(Object o, long l, boolean b) throws Exception {
        System.out.println(Thread.currentThread().getName() + " Element12: " + o.toString());
        if (o instanceof Message){
            ThreadManager.getInstance().getConsumerExecutor().execute(new DisruptorConsumer((Message)o));
        }else{
            throw new RuntimeException("消费消息类型异常");
        }
    }
}
