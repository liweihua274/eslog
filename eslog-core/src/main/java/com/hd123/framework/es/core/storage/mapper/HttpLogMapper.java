package com.hd123.framework.es.core.storage.mapper;

import com.hd123.framework.es.core.storage.entity.HttpLog;
import com.hd123.framework.es.core.storage.entity.HttpLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface HttpLogMapper {
    long countByExample(HttpLogExample example);

    int deleteByExample(HttpLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(HttpLog record);

    int insertSelective(HttpLog record);

    List<HttpLog> selectByExampleWithRowbounds(HttpLogExample example, RowBounds rowBounds);

    List<HttpLog> selectByExample(HttpLogExample example);

    HttpLog selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") HttpLog record, @Param("example") HttpLogExample example);

    int updateByExample(@Param("record") HttpLog record, @Param("example") HttpLogExample example);

    int updateByPrimaryKeySelective(HttpLog record);

    int updateByPrimaryKey(HttpLog record);

    Long sumByExample(HttpLogExample example);

    void batchInsert(@Param("items") List<HttpLog> items);
}