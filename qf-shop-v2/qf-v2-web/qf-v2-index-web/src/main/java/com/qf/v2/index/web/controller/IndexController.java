package com.qf.v2.index.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.service.ProductTypeService;
import com.qf.v2.entity.ProductType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {
   @Reference
    private ProductTypeService productTypeService;

   @RequestMapping("showIndex")
    public String showIndex(Model model){

       List<ProductType> productTypes = productTypeService.selectProducts();

       model.addAttribute("productTypes",productTypes);

       return "index";
   }

}
