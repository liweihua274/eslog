package com.hd123.framework.es.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by LIWEIHUA on 2018-03-27.
 */

public class StorageConfig {

    public enum storage{
        kafka(1, "kafka"),
        disruptor(2, "disruptor");
        private int index;
        private String name;
        storage(int index, String name){
            this.index = index;
            this.name = name;
        }

        public int getIndex() {
            return index;
        }

        public String getName() {
            return name;
        }
    }
    public static String MESSAGE_STORAGE = storage.disruptor.getName();






}
