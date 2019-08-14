package com.qf.item.api;

import com.qf.v2.common.vo.ResultVO;
import com.qf.v2.entity.Product;

import java.util.List;

public interface ItemService  {
    ResultVO createItemPage(Product product);
    ResultVO batchCreatePage(List<Product> products);
}
