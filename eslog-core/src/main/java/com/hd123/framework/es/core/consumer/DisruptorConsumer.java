package com.hd123.framework.es.core.consumer;

import com.hd123.framework.es.core.message.Message;
import com.hd123.framework.es.core.message.PMessage;
import com.hd123.framework.es.core.storage.MysqlStorage;
import com.hd123.framework.es.core.storage.Storage;
import com.hd123.framework.es.core.utils.SpringContextUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author liweihua
 *
 */
public class DisruptorConsumer extends Consumer implements Runnable {

    private final Message message;



    public DisruptorConsumer(Message message){

        this.message = message;
    }

    public Message fetch() {
        return null;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " Element12:11111 ");
        PMessage pMessage = new PMessage();
        BeanUtils.copyProperties(message, pMessage);
        Object mysqlStorage = SpringContextUtil.getBean("mysqlService");
        if (mysqlStorage instanceof MysqlStorage){
            System.out.println("操作正常进行");
            ((MysqlStorage) mysqlStorage).save(pMessage);
        }else{
            System.out.println("找不到bean");
        }


    }
}
