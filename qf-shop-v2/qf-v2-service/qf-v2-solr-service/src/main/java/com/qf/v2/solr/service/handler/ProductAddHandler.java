package com.qf.v2.solr.service.handler;

import com.lzg.solr.service.SolrService;
import com.lzg.solr.service.entity.ProductResult;
import com.qf.v2.common.constant.RabbitConstant;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductAddHandler {

    @Autowired
    private SolrService service;
    @RabbitListener(queues = RabbitConstant.PRODUCT_SEARCH_SAVE_UPDATE_QUEUE)
    public void process(ProductResult product){
        service.addProductToSearch(product);
    }
}
