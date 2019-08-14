package com.lzg.solr.service;

import com.lzg.solr.service.entity.ProductResult;
import com.qf.v2.common.vo.ResultVO;

import java.util.List;

public interface SolrService {
    ResultVO<List<ProductResult>> searchByKeywords(String keywords,int start,int rows);

    ResultVO addProductToSearch(ProductResult product);

}
