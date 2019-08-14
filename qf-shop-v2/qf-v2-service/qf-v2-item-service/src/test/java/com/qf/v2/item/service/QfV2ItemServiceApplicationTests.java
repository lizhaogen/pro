package com.qf.v2.item.service;

import com.qf.v2.entity.Product;
import com.qf.v2.item.service.impl.ItemServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QfV2ItemServiceApplicationTests {

    @Autowired
    private ItemServiceImpl service;

    @Test
    public void contextLoads() {
        Product product = new Product();
        product.setId(5);
        product.setName("huangse");
        product.setPrice(66);
        product.setSalePrice(55);
        product.setPimage("aaa");
        service.createItemPage(product);
    }

}
