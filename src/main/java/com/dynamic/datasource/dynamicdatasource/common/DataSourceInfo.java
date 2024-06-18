package com.dynamic.datasource.dynamicdatasource.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: vincent
 * @License: (C) Copyright 2005-2200, vincent Corporation Limited.
 * @Contact: lookvincent@163.com
 * @Date: 6/18/24 15:26
 * @Version: 1.0
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataSourceInfo {

    private String userName;
    private String passWord;
    private String url;
    private String dataSourceKey;

}
