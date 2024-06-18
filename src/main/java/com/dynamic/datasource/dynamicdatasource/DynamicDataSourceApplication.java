package com.dynamic.datasource.dynamicdatasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dynamic.datasource.dynamicdatasource.mapper")
public class DynamicDataSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicDataSourceApplication.class, args);
    }

}
