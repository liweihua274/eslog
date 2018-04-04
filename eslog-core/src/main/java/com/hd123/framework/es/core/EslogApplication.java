package com.hd123.framework.es.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by LIWEIHUA on 2018-03-23.
 *
 */

@ComponentScan(basePackages = {"com.hd123.framework.es"})
@MapperScan(basePackages = {"com.hd123.framework.es.core.storage.mapper"})
@EnableAutoConfiguration
@SpringBootApplication
public class EslogApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(EslogApplication.class);
        springApplication.run(args);
  /*
        DisruptorHandler disruptorHandler = DisruptorHandler.getInsatance();
        disruptorHandler.init();
        for (long l=0; l<100000; l++) {
            Message message = new Message();
            message.setTopic("测试数据：" + String.valueOf(l));
            disruptorHandler.publish(message);
        }
*/
    }
}
