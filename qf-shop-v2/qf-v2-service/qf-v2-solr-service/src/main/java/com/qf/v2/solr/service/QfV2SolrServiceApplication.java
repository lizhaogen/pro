package com.qf.v2.solr.service;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
@MapperScan("com.qf.v2.solr.service.mapper")
public class QfV2SolrServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QfV2SolrServiceApplication.class, args);
    }

}
