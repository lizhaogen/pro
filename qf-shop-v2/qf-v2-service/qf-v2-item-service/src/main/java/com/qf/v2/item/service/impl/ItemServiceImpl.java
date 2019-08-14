package com.qf.v2.item.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.item.api.ItemService;
import com.qf.v2.common.vo.ResultVO;
import com.qf.v2.entity.Product;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Component
@Service
public class ItemServiceImpl implements ItemService {

    //模板  数据 得到静态页面
    @Autowired
    private Configuration configuration;

    @Value("${template.path}")
    private String path;

    private static final Integer CORE_THREAD= Runtime.getRuntime().availableProcessors();

    private ExecutorService pool = new ThreadPoolExecutor(CORE_THREAD,CORE_THREAD*2,200L,
            TimeUnit.MILLISECONDS,new LinkedBlockingDeque<Runnable>(1000));

    class CreatePageCallable implements Callable<Boolean>{

        private Product product;
        public CreatePageCallable(Product product){
            this.product=product;
        }

        @Override
        public Boolean call() throws Exception {
            Template template = configuration.getTemplate("detail.ftl");
            Map<String,Object> data = new HashMap<>();
            data.put("product",product);
            Writer out = new FileWriter(path+product.getId()+".html");
            template.process(data,out);

            return true;
        }
    }

    public ResultVO batchCreatePage(List<Product> products){
        //存放线程执行结果的集合
        List<Future<Boolean>> futures = new ArrayList<>();
        for (Product product : products) {
            //将任务交给线程池 获得执行结果
            Future<Boolean> submit = pool.submit(new CreatePageCallable(product));
            //结果存入到集合
            futures.add(submit);

        }
        return ResultVO.successResult();

    }





    @Override
    public ResultVO createItemPage(Product product) {
        try {
            Template template = configuration.getTemplate("detail.ftl");

            Map<String,Object> data = new HashMap<>();
            data.put("product",product);
            Writer out = new FileWriter(path+product.getId()+".html");
            template.process(data,out);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
            return ResultVO.errorResult();

        }

        return ResultVO.successResult("ok");
    }
}
