package com.qf.v2.user.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QfV2UserWebApplicationTests {

    //使用security生成密文 MD5
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void contextLoads() {
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);

    }

}
