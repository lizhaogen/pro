package com.qf.v2.solr.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lzg.solr.service.SolrService;
import com.lzg.solr.service.entity.ProductResult;
import com.qf.v2.common.vo.ResultVO;
import com.qf.v2.solr.service.mapper.ProductResultMapper;
import org.apache.solr.client.solrj.SolrClient;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Component
@Service
public class SearchServiceImpl implements SolrService {

    @Autowired
    private SolrClient client;

    @Autowired
    private ProductResultMapper mapper;
    //初始化索引库，把数据库所有数据都添加进去
    public void initSolrData(){
        List<ProductResult> products = mapper.selectAll();
        for (ProductResult product : products) {
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id",product.getId());
            document.addField("product_name",product.getProductName());
            document.addField("product_sale_point",product.getProductSalePoint());
            document.addField("product_image", product.getProductImage());
            document.addField("product_price",product.getProductPrice());
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




    @Override
    public ResultVO<List<ProductResult>> searchByKeywords(String keywords, int start, int rows) {
        SolrQuery query = new SolrQuery();
        query.setQuery(keywords);
        query.setStart(start);
        query.setRows(rows);
        query.set("df","product_keywords");
        query.setHighlight(true);
        query.addHighlightField("product_name");
        query.setHighlightSimplePre("<span style='color:red;'>");
        query.setHighlightSimplePost("</span>");
        List<ProductResult> list =new ArrayList<>();

        try {
            QueryResponse res = client.query(query);
            Map<String, Map<String, List<String>>> highlighting = res.getHighlighting();
            SolrDocumentList results = res.getResults();
            for (SolrDocument document : results) {
                ProductResult product = new ProductResult();
                String id = (String) document.getFieldValue("id");
                product.setId(Integer.parseInt(id));
                product.setProductName((String) document.getFieldValue("product_name"));
                Map<String, List<String>> id1 = highlighting.get(document.getFieldValue("id"));
                if (id1 != null){
                    List<String> str = id1.get("product_name");
                    if (str != null && str.size()>0){
                        String hsl = str.get(0);
                        product.setProductName(hsl);
                    }
                }
                product.setProductSalePoint((String) document.getFieldValue("product_sale_point"));
                product.setProductInfo((String) document.getFieldValue("product_info"));
                product.setProductImage((String) document.getFieldValue("product_image"));
                product.setProductPrice(Integer.parseInt((String) document.getFieldValue("product_price")));
                list.add(product);

            }


        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ResultVO<List<ProductResult>> resultVO = new ResultVO<>();
        resultVO.setResult(true);
        resultVO.setMessage("");
        resultVO.setData(list);

        return resultVO;
    }

    @Override
    public ResultVO addProductToSearch(ProductResult product) {
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id",product.getId());
        document.addField("product_name",product.getProductName());
        document.addField("product_sale_point",product.getProductSalePoint());
        document.addField("product_image",product.getProductImage());
        document.addField("product_price",product.getProductPrice());
        document.addField("product_info",product.getProductInfo());


        try {
            client.add(document);
            client.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            return  ResultVO.errorResult();

        }


        return ResultVO.successResult("插入成功");
    }
}
