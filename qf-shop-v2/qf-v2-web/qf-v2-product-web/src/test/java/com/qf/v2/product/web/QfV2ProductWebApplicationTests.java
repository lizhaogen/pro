package com.qf.v2.product.web;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QfV2ProductWebApplicationTests {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Test
    public void contextLoads() throws FileNotFoundException {
       File file = new File("D:\\My Code Of IDEA\\qf-shop-v2\\qf-v2-web\\qf-v2-product-web\\src\\main\\resources\\static\\2.jpg");
       FileInputStream fis = new FileInputStream(file);
        StorePath path = fastFileStorageClient.uploadImageAndCrtThumbImage(fis, file.length(), "jpg", null);
        System.out.println(path);

    }
    @Test
    public void testFileName(){
        String name ="2.jpg";
        String substring =name.substring(name.lastIndexOf(".")+1);
        System.out.println(substring);


    }

}
