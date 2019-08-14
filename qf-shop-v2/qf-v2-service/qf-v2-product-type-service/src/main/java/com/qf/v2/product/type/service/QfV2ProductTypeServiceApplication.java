package com.qf.v2.product.type.service;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
@MapperScan("com.qf.v2.mapper")
public class QfV2ProductTypeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QfV2ProductTypeServiceApplication.class, args);
    }

}
