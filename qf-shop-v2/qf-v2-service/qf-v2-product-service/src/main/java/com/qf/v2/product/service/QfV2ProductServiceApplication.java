package com.qf.v2.product.service;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.qf.v2.mapper")
@EnableDubbo
public class QfV2ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QfV2ProductServiceApplication.class, args);
    }

}
