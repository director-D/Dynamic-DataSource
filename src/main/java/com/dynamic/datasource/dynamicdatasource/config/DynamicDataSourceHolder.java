package com.dynamic.datasource.dynamicdatasource.config;

import com.dynamic.datasource.dynamicdatasource.common.CommonConstant;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: vincent
 * @License: (C) Copyright 2005-2200, vincent Corporation Limited.
 * @Contact: lookvincent@163.com
 * @Date: 6/18/24 15:03
 * @Version: 1.0
 * @Description:
 */

@Slf4j
public class DynamicDataSourceHolder {

    private static final ThreadLocal<String> DYNAMIC_DATASOURCE_KEY = new ThreadLocal<>();


    public static void setDynamicDatasourceKey(String key){
        log.info("数据源切换为：{}" , key);
        DYNAMIC_DATASOURCE_KEY.set(key);
    }


    public static String getDynamicDatasourceKey(){
        String s = DYNAMIC_DATASOURCE_KEY.get();
        return s == null ? CommonConstant.MASTER :s;
    }


    public static void remove(){
        log.info("移除数据源：{}",DYNAMIC_DATASOURCE_KEY.get());
        DYNAMIC_DATASOURCE_KEY.remove();
    }


}
