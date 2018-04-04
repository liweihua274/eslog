package com.hd123.framework.es.starter;

import com.hd123.framework.es.core.storage.MysqlStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by LIWEIHUA on 2018-04-04.
 * todo
 */
@Configuration
public class EslogAutoConfiguration {

    @Bean
    public MysqlStorage getMysqlStorage(){
        MysqlStorage mysqlStorage = new MysqlStorage();
        return mysqlStorage;
    }




}
