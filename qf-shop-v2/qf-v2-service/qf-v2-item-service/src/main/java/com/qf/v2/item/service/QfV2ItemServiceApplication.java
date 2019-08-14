package com.qf.v2.item.service;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class QfV2ItemServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QfV2ItemServiceApplication.class, args);
    }

}
