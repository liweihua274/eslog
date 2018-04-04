package com.hd123.framework.es.core.message;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by LIWEIHUA on 2018-03-23.
 *日志消息，生产消费消息
 */
public class Message implements Serializable{

    //private static final long serialVersionUID = -4396618147611330427L;

    private String topic;

    private Map<String, String> properties;

    private byte[] body;


    @Override
    public String toString() {
        return "Message [topic=" + topic +  ", properties=" + properties + ", body="
                + (body != null ? body.length : 0) + "]";
    }


    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
