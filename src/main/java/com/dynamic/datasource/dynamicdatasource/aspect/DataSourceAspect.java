package com.dynamic.datasource.dynamicdatasource.aspect;

import com.dynamic.datasource.dynamicdatasource.config.DynamicDataSourceHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Author: vincent
 * @License: (C) Copyright 2005-2200, vincent Corporation Limited.
 * @Contact: lookvincent@163.com
 * @Date: 6/18/24 16:57
 * @Version: 1.0
 * @Description:
 */
@Aspect
@Service
public class DataSourceAspect {


    @Pointcut(value = "@annotation(com.dynamic.datasource.dynamicdatasource.aspect.Ds)")
    public void dynamicDataSourcePointCut(){}


    //环绕通知
    @Around("dynamicDataSourcePointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        //获取数据源的key
        String key = getDefineAnnotation(joinPoint).value();
        //将数据源设置为该key的数据源
        DynamicDataSourceHolder.setDynamicDatasourceKey(key);
        try {
            return joinPoint.proceed();
        } finally {
            //使用完成后切回master
            DynamicDataSourceHolder.remove();
        }
    }


    /**
     * 先判断方法的注解，后判断类的注解，以方法的注解为准
     * @param joinPoint
     * @return
     */
    private Ds getDefineAnnotation(ProceedingJoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Ds dataSourceAnnotation = methodSignature.getMethod().getAnnotation(Ds.class);
        if (Objects.nonNull(methodSignature)) {
            return dataSourceAnnotation;
        } else {
            Class<?> dsClass = joinPoint.getTarget().getClass();
            return dsClass.getAnnotation(Ds.class);
        }
    }




}
