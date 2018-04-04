package com.hd123.framework.es.core.test;

import com.hd123.framework.es.core.annotation.Eslog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by LIWEIHUA on 2018-04-02.
 */
@RequestMapping("/api")
@RestController
public class TestController {
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @Eslog
    public void init(){
        System.out.println("122222");
    }
}
