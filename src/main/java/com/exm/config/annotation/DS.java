package com.exm.config.annotation;

import com.exm.config.datasource.DSType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yyx
 * @version 1.0
 * @createDate 2018-12-12 17:34
 * @description
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DS {

    String value() default DSType.Master;
}
