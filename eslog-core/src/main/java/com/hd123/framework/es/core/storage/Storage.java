package com.hd123.framework.es.core.storage;

import com.hd123.framework.es.core.message.PMessage;

/**
 * Created by LIWEIHUA on 2018-03-23.
 * 抽象持久层
 */
public interface Storage {

    /**
     * 保存消息
     */
     void  save(PMessage pMessage);

}
