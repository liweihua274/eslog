package com.hd123.framework.es.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by LIWEIHUA on 2018-04-02.
 */

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface Eslog {
}
