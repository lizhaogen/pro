package com.qf.v2.user.web;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;


@SpringBootApplication
@EnableDubbo
@CrossOrigin(origins = "*", maxAge = 3600)
public class QfV2UserWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(QfV2UserWebApplication.class, args);
    }

}
