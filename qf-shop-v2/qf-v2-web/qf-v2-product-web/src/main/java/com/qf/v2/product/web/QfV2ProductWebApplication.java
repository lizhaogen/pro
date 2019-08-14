package com.qf.v2.product.web;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import java.io.IOException;

@Import(FdfsClientConfig.class)
@SpringBootApplication
@EnableDubbo
public class QfV2ProductWebApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(QfV2ProductWebApplication.class, args);
        System.in.read();
    }

}
