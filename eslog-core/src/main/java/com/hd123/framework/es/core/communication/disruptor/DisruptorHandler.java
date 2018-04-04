package com.hd123.framework.es.core.communication.disruptor;

import com.hd123.framework.es.core.common.ThreadManager;
import com.hd123.framework.es.core.consumer.ConsumerDisruptorHandler;
import com.hd123.framework.es.core.message.Message;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

/**
 * Created by LIWEIHUA on 2018-03-28.
 *
 */
public class DisruptorHandler {

    private Disruptor<Message> disruptor;

    private static class SingletonHolder{
        private static final DisruptorHandler instance = new DisruptorHandler();
    }
    private DisruptorHandler(){
        init();
    }

    public static final DisruptorHandler getInsatance(){
        return SingletonHolder.instance;
    }

    public void init(){
        // RingBuffer生产工厂,初始化RingBuffer的时候使用
        EventFactory<Message> factory = new EventFactory<Message>() {
            public Message newInstance() {
                return new Message();
            }
        };
        // 处理Event的handler
        EventHandler<Message> handler = new ConsumerDisruptorHandler();
        //BlockingWaitStrategy 是最低效的策略，但其对CPU的消耗最小并且在各种不同部署环境中能提供更加一致的性能表现；
        //SleepingWaitStrategy 的性能表现跟 BlockingWaitStrategy 差不多，对 CPU 的消耗也类似，但其对生产者线程的影响最小，适合用于异步日志类似的场景；
        //YieldingWaitStrategy 的性能是最好的，适合用于低延迟的系统。在要求极高性能且事件处理线数小于 CPU 逻辑核心数的场景中，推荐使用此策略；例如，CPU开启超线程的特性。
        SleepingWaitStrategy  strategy = new SleepingWaitStrategy();
        // 指定RingBuffer的大小
        int bufferSize = 16;
        // 创建disruptor
        disruptor = new Disruptor(factory, bufferSize, ThreadManager.getInstance().getConsumerExecutor(),
                ProducerType.MULTI, strategy);
        // 设置EventHandler
        disruptor.handleEventsWith(handler);
        // 启动disruptor的线程
        disruptor.start();

    }

    public void publish(Message message){
        RingBuffer<Message> ringBuffer = disruptor.getRingBuffer();
        long sequence = ringBuffer.next();//请求下一个事件序号；

        try {
            Message event = ringBuffer.get(sequence);//获取该序号对应的事件对象；
            System.out.println(message.toString());
            event.setTopic(message.toString());
        } finally{
            ringBuffer.publish(sequence);//发布事件；
        }
    }

    public static void main(String[] args){
        DisruptorHandler disruptorHandler = new DisruptorHandler();
        disruptorHandler.init();
        for (long l=0; l<100000; l++) {
            Message message = new Message();
            message.setTopic("测试数据：" + String.valueOf(l));
            disruptorHandler.publish(message);
        }
    }

}
