package com.qf.v2.product.web.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.lzg.solr.service.SolrService;
import com.lzg.solr.service.entity.ProductResult;
import com.qf.item.api.ItemService;
import com.qf.v2.common.constant.RabbitConstant;
import com.qf.v2.common.dto.ProductDTO;
import com.qf.v2.common.vo.ResultVO;
import com.qf.v2.entity.Product;
import com.qf.v2.service.ProductService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.spec.RC2ParameterSpec;
import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {
   @Reference
    private ProductService productService;
   @Reference
   private SolrService solrService;
   @Reference
   private ItemService itemService;

   @Autowired
   private RabbitTemplate rabbitTemplate;


    @RequestMapping("getPro/{id}")
    @ResponseBody
    public Product getProduct(@PathVariable Integer id){
        return productService.getProduct(id);
    }




    @RequestMapping("list/{pageNum}/{pageSize}")
    public String getIndex(Model model,@PathVariable int pageNum,@PathVariable int pageSize){

        List<Product> products= productService.selectProducts();

        model.addAttribute("list",products);

        PageInfo<Product> pageInfo=productService.getPageInfo(pageNum,pageSize);

        model.addAttribute("pageInfo",pageInfo);

        return "product/index";
    }

    //添加商品
    @RequestMapping(value = "add",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO addProduct(ProductDTO product){
        Integer id = productService.addProduct(product);
        //生成静态页面
        Product nPro = product.getProduct();
        //
        nPro.setId(id);
       // ResultVO vo = itemService.createItemPage(nPro);
        rabbitTemplate.convertAndSend(RabbitConstant.PRODUCT_WEB_EXCHANGE,
                RabbitConstant.ITEM_SERVICE_ADD_ROUTING_KEY,nPro);



        //添加到索引库
        ProductResult productResult = new ProductResult();
        productResult.setId(id);
        productResult.setProductName(product.getProduct().getName());
        productResult.setProductSalePoint(product.getProduct().getSalePoint());
        productResult.setProductImage(product.getProduct().getPimage());
        productResult.setProductPrice(product.getProduct().getPrice());
        productResult.setProductInfo(product.getProductInfo());

        //ResultVO resultVO = solrService.addProductToSearch(productResult);
        rabbitTemplate.convertAndSend(RabbitConstant.PRODUCT_WEB_EXCHANGE,
                RabbitConstant.SEARCH_SERVICE_ADD_ROUTING_KEY,productResult);

        return ResultVO.successResult();
    }

    @RequestMapping("batchCreatePage")
    @ResponseBody
    public ResultVO batchCreatePage(){
        List<Product> products = productService.selectProducts();


        ResultVO resultVO = itemService.batchCreatePage(products);


        return resultVO;
    }


}
