package com.qf.v2.item.service.handler;

import com.qf.item.api.ItemService;
import com.qf.v2.common.constant.RabbitConstant;
import com.qf.v2.entity.Product;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductAddHandler {
    @Autowired
    private ItemService itemService;
    @RabbitListener(queues = RabbitConstant.PRODUCT_ITEM_SAVE_UPDATE_QUEUE)
    public void processProductAdd(Product product){
        itemService.createItemPage(product);
    }
}
