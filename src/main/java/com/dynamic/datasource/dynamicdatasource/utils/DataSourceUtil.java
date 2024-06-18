package com.dynamic.datasource.dynamicdatasource.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.dynamic.datasource.dynamicdatasource.common.DataSourceInfo;
import com.dynamic.datasource.dynamicdatasource.config.DynamicDataSource;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.annotation.Retention;
import java.sql.SQLException;
import java.util.Map;

/**
 * @Author: vincent
 * @License: (C) Copyright 2005-2200, vincent Corporation Limited.
 * @Contact: lookvincent@163.com
 * @Date: 6/18/24 15:21
 * @Version: 1.0
 * @Description:
 */

@Slf4j
@Component
public class DataSourceUtil {


    @Resource
    private DynamicDataSource dynamicDataSource;


    /**
     * @Description: 根据传递的数据源信息测试数据库连接
     * @Author zhangyu
     */
    public DruidDataSource createDataSourceConnection(DataSourceInfo dataSourceInfo) {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(dataSourceInfo.getUrl());
        druidDataSource.setUsername(dataSourceInfo.getUserName());
        druidDataSource.setPassword(dataSourceInfo.getPassWord());
        druidDataSource.setBreakAfterAcquireFailure(true);
        druidDataSource.setConnectionErrorRetryAttempts(0);
        try {
            druidDataSource.getConnection(2000);
            log.info("数据源连接成功");
            return druidDataSource;
        } catch (SQLException throwables) {
            log.error("数据源 {} 连接失败,用户名：{}，密码 {}"
                    ,dataSourceInfo.getUrl(),dataSourceInfo.getUserName(),dataSourceInfo.getPassWord());
            return null;
        }
    }

    /**
     * 将外部数据源存到dynamicDataSource并调用afterPropertiesSet刷新
     * @param druidDataSource
     * @param dataSourceName
     */
    public void addDefineDynamicDataSource(DruidDataSource druidDataSource, String dataSourceName){
        Map<Object, Object> defineTargetDataSources = dynamicDataSource.getDefineTargetDataSources();
        //存到defineTargetDataSources这个map中
        defineTargetDataSources.put(dataSourceName, druidDataSource);
        dynamicDataSource.setTargetDataSources(defineTargetDataSources);
        //调用afterPropertiesSet重新遍历map中的数据源键值对存到resolvedDataSources中
        dynamicDataSource.afterPropertiesSet();
    }






}
