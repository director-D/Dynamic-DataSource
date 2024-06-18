package com.dynamic.datasource.dynamicdatasource.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.dynamic.datasource.dynamicdatasource.common.CommonConstant;
import com.dynamic.datasource.dynamicdatasource.common.DataSourceInfo;
import com.dynamic.datasource.dynamicdatasource.config.DynamicDataSourceHolder;
import com.dynamic.datasource.dynamicdatasource.mapper.CommonMapper;
import com.dynamic.datasource.dynamicdatasource.model.Car;
import com.dynamic.datasource.dynamicdatasource.model.User;
import com.dynamic.datasource.dynamicdatasource.utils.DataSourceUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: vincent
 * @License: (C) Copyright 2005-2200, vincent Corporation Limited.
 * @Contact: lookvincent@163.com
 * @Date: 6/18/24 15:30
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@RestController
public class TestController {

    @Resource
    CommonMapper commonMapper;

    @Resource
    DataSourceUtil dataSourceUtil;


    @GetMapping("/test")
    public Map<String, Object> dynamicDataSourceTest(){

        Map<String,Object> result = new HashMap<>();
        //在主库中查询汽车信息列表
        List<Car> carList = commonMapper.getCarInfo();
        carList.forEach(car -> {
            log.info("汽车信息：{}",car);
        });
        result.put("car1",carList);

        List<User> userInfo = commonMapper.getUserInfo();
        userInfo.forEach(user -> {
            log.info("用户信息：{}",user);
        });
        result.put("user1",userInfo);


        //在从库中查询数据源信息
//        DynamicDataSourceHolder.setDynamicDatasourceKey(CommonConstant.SLAVE);
//        DataSourceInfo dataSourceInfo = CommonMapper.getNewDataSourceInfo(CommonConstant.SLAVE);
//        map.put("dataSource",dataSourceInfo);
//        log.info("数据源信息：{}",dataSourceInfo);
        //测试数据源连接
//        DruidDataSource druidDataSource = dataSourceUtil.createDataSourceConnection(dataSourceInfo);
//        if (Objects.nonNull(druidDataSource)){
            //将新的数据源连接添加到目标数据源map中
//            dataSourceUtil.addDefineDynamicDataSource(druidDataSource,dataSourceInfo.getDataSourceKey());
//        }
        //设置当前线程数据源名称-----代码形式
//        DynamicDataSourceHolder.setDynamicDatasourceKey(CommonConstant.SLAVE);
        //在主库中查询汽车信息列表
        List<Car> carList1 = commonMapper.getCarInfo();
        result.put("car2",carList1);
        //在新的数据源中查询用户信息
        carList1.forEach(car -> {
            log.info("汽车信息：{}",car);
        });
        List<User> userList = commonMapper.getUserInfo();
        result.put("user2",userList);
        userList.forEach(user -> {
            log.info("用户信息：{}",user);
        });
        //关闭数据源连接
//        druidDataSource.close();
        return result;
    }

}
