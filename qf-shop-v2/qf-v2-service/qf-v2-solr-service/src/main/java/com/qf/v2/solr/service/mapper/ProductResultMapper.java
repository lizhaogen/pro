package com.qf.v2.solr.service.mapper;

import com.lzg.solr.service.entity.ProductResult;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductResultMapper {
    List<ProductResult> selectAll();
}
