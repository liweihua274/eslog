package com.hd123.framework.es.core.log;

import com.hd123.framework.es.client.log.Logger;
import com.hd123.framework.es.core.config.StorageConfig;
import com.hd123.framework.es.core.message.Message;
import com.hd123.framework.es.core.producer.KafkaProducer;
import com.hd123.framework.es.core.producer.Producer;
import com.hd123.framework.es.core.producer.DisruptorProducer;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Created by LIWEIHUA on 2018-03-27.
 * 此类是bean,需要注入
 */

@Component
public class LoggerImpl implements Logger {


    public LoggerImpl(){

    }

    public void info(String msg) {
        writeLog(msg);
    }

    public void error(String msg) {

    }

    public void debug(String msg) {

    }

    public void write(String msg, Integer level) {

    }

    /**
     * 针对http
     * @param request
     * @param response
     */
    public void write(String request, String response) {

    }

    private void writeLog(String msg){
        Assert.hasLength(msg, "消息不能为空");
        Message message = new Message();
        message.setTopic(msg);
        getProducer().send(message);
    }





    private Producer getProducer(){
        if (StorageConfig.MESSAGE_STORAGE.equals("kafka")){
            return new KafkaProducer();
        }else if (StorageConfig.MESSAGE_STORAGE.equals("disruptor")){
            return new DisruptorProducer();
        }else{
            throw new RuntimeException("配置消息传输类型参数异常");
        }
    }


}
