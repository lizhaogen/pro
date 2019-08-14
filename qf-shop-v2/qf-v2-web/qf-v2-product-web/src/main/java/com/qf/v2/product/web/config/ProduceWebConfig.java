package com.qf.v2.product.web.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class ProduceWebConfig {
    @Bean
    public CommonsMultipartResolver getCMR(){
        CommonsMultipartResolver cmr = new CommonsMultipartResolver();
        cmr.setMaxUploadSize(10485760L);
        return cmr;
    }

}
