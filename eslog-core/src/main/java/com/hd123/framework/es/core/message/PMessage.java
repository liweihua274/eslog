package com.hd123.framework.es.core.message;

import java.util.Map;

/**
 * Created by LIWEIHUA on 2018-03-29.
 */
public class PMessage {
    private String topic;

    private Map<String, String> properties;

    private byte[] body;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }
}
