package com.dynamic.datasource.dynamicdatasource.aspect;

import com.dynamic.datasource.dynamicdatasource.common.CommonConstant;

import java.lang.annotation.*;

/**
 * @Author: vincent
 * @License: (C) Copyright 2005-2200, vincent Corporation Limited.
 * @Contact: lookvincent@163.com
 * @Date: 6/18/24 16:50
 * @Version: 1.0
 * @Description:
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Ds {

    /**
     * 切换数据源名称
     */
    public String value() default CommonConstant.MASTER;


}
