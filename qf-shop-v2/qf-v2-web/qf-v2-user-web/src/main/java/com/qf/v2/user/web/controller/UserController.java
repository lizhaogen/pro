package com.qf.v2.user.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.user.api.UserService;
import com.qf.v2.common.constant.CookieConstant;
import com.qf.v2.common.constant.RedisConstant;
import com.qf.v2.common.vo.ResultVO;
import com.qf.v2.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    @Reference
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping("login")
    public String login(){
        return "login";
    }

    @RequestMapping("checkLogin")
    public String checkLogin(User user, HttpServletResponse response){
       User currentUser= userService.checkLogin(user);

       if (currentUser != null){
           String uuid = UUID.randomUUID().toString();

           String key = new StringBuffer(RedisConstant.USER_TOKEN).append(uuid).toString();

            //修改key的序列化器
           redisTemplate.setKeySerializer(new StringRedisSerializer());

           //存入到redis中
           redisTemplate.opsForValue().set(key,currentUser);

           //设置有效时间
           redisTemplate.expire(key,30, TimeUnit.MINUTES);

           //存入到cookie中
           Cookie cookie = new Cookie(CookieConstant.USER_TOKEN,uuid);
           cookie.setPath("/");
           cookie.setHttpOnly(true);
           cookie.setDomain("qf.com");

           //将cookie发送到客户端
           response.addCookie(cookie);



        return "redirect:http://localhost:8096/showIndex";
       }
       return "login";
    }

    @RequestMapping("checkIsLogin")
    @ResponseBody
    public ResultVO checkIsLogin(@CookieValue(name = CookieConstant.USER_TOKEN,required = false) String uuid) {
        ResultVO resultVO = userService.checkIsLogin(uuid);
        return resultVO;
    }


    @RequestMapping("logout")
    @ResponseBody
    public ResultVO logout(@CookieValue(name = CookieConstant.USER_TOKEN,required = false)String uuid,HttpServletResponse response){
        if (uuid == null){
            return new ResultVO(false,"该用户未登录",null);

        }

        String key = new StringBuffer(RedisConstant.USER_TOKEN).append(uuid).toString();

        redisTemplate.setKeySerializer(new StringRedisSerializer());

        redisTemplate.delete(key);

        Cookie cookie = new Cookie(CookieConstant.USER_TOKEN, uuid);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setDomain("qf.com");
        response.addCookie(cookie);

        return new ResultVO(true,"注销成功",null);
    }

    @RequestMapping("testRegister")
    public String Register(){
        return "register";
    }
    @RequestMapping("register")
    public ResultVO register(User user){


        return ResultVO.successResult();
    }



}
