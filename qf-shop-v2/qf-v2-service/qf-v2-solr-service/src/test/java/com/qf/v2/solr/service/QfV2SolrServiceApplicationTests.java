package com.qf.v2.solr.service;

import com.lzg.solr.service.SolrService;
import com.qf.v2.solr.service.impl.SearchServiceImpl;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QfV2SolrServiceApplicationTests {

    @Autowired
    private SolrClient client;

    @Autowired
    private SearchServiceImpl service;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testAddAll(){
        service.initSolrData();
    }

    @Test
    public void testAddData(){
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id",1002);
        document.addField("product_name","三星 I8553 粉色 联通3G手机 双卡双待");
        document.addField("product_sale_point","经济实惠机器~~开春入手好时机~");
        document.addField("product_info","棒！");
        document.addField("product_image","pic/1.jpg");
        document.addField("product_price",6999);
        try {
            client.add(document);
            client.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
