package com.dynamic.datasource.dynamicdatasource.mapper;

import com.alibaba.druid.pool.DruidDataSource;
import com.dynamic.datasource.dynamicdatasource.aspect.Ds;
import com.dynamic.datasource.dynamicdatasource.common.DataSourceInfo;
import com.dynamic.datasource.dynamicdatasource.model.Car;
import com.dynamic.datasource.dynamicdatasource.model.User;
import com.dynamic.datasource.dynamicdatasource.utils.DataSourceUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: vincent
 * @License: (C) Copyright 2005-2200, vincent Corporation Limited.
 * @Contact: lookvincent@163.com
 * @Date: 6/18/24 15:33
 * @Version: 1.0
 * @Description:
 */
@Mapper
public interface CommonMapper {

    @Ds
    @Select("select * from car")
    List<Car> getCarInfo();


    static DataSourceInfo getNewDataSourceInfo(String dataKey){
        DataSourceInfo dataSourceInfo = new DataSourceInfo();
        dataSourceInfo.setUserName("root");
        dataSourceInfo.setPassWord("123");
        dataSourceInfo.setUrl("jdbc:mysql://localhost:3306/db2?useUnicode=true&characterEncoding=utf-8&useSSL=false");
        dataSourceInfo.setDataSourceKey(dataKey);
        return dataSourceInfo;
    }

    @Ds("slave")
    @Select("select * from user")
    List<User> getUserInfo();


}
