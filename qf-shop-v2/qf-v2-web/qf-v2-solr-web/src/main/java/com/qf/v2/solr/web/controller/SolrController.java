package com.qf.v2.solr.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lzg.solr.service.SolrService;
import com.lzg.solr.service.entity.ProductResult;
import com.qf.v2.common.vo.ResultVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("search")
public class SolrController {
    @Reference
    private SolrService solrService;


    @RequestMapping("list")
    public String list(){
        return "search";

    }


    @RequestMapping("searchByKeywords")
    public String searchByKeyWords(String keywords, Model model){
        ResultVO<List<ProductResult>> resultVO = solrService.searchByKeywords(keywords, 0, 12);

        model.addAttribute("products",resultVO.getData());
        return "search";


    }


}
