package com.dynamic.datasource.dynamicdatasource.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * @Author: vincent
 * @License: (C) Copyright 2005-2200, vincent Corporation Limited.
 * @Contact: lookvincent@163.com
 * @Date: 6/18/24 15:00
 * @Version: 1.0
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Service
public class DynamicDataSource extends AbstractRoutingDataSource {

    private Map<Object,Object> defineTargetDataSources;


    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getDynamicDatasourceKey();
    }


}
