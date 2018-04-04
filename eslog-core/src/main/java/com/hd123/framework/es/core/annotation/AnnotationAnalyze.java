package com.hd123.framework.es.core.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by LIWEIHUA on 2018-04-02.
 * todo
 */
public class AnnotationAnalyze {

    public static void init(Object object){
        Annotation annotation = object.getClass().getAnnotation(Eslog.class);
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {

        }
    }
}
