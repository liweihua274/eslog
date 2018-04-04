package com.hd123.framework.es.core.storage;

import com.dangdang.ddframe.rdb.sharding.id.generator.IdGenerator;
import com.hd123.framework.es.core.message.PMessage;
import com.hd123.framework.es.core.storage.entity.HttpLog;
import com.hd123.framework.es.core.storage.mapper.HttpLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by LIWEIHUA on 2018-03-23.
 * 保存到mysql
 */


public class MysqlStorage implements Storage{


    @Autowired
    HttpLogMapper httpLogMapper;

    @Autowired
    private IdGenerator idGenerator;

    public void save(PMessage pMessage) {
        System.out.println("mysqlservice1");
        HttpLog httpLog = new HttpLog();
        httpLog.setAppId("aaa");
        httpLog.setExceptionContent(pMessage.getTopic());
        httpLog.setBeginTime(new Date());
        httpLog.setLevel((byte)0);
        httpLog.setId(idGenerator.generateId().toString());
        httpLogMapper.insert(httpLog);
        System.out.println("mysqlservice2");
    }
}
