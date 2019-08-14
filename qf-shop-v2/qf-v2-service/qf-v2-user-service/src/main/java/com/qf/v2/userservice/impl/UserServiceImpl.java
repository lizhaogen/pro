package com.qf.v2.userservice.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.user.api.UserService;
import com.qf.v2.common.base.BaseDao;
import com.qf.v2.common.base.BaseServiceImpl;
import com.qf.v2.common.constant.RedisConstant;
import com.qf.v2.common.vo.ResultVO;
import com.qf.v2.entity.User;
import com.qf.v2.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public BaseDao<User> getBaseDao() {
        return userMapper;
    }

    //数据库验证用户是否存在
    @Override
    public User checkLogin(User user) {
        String username = user.getUsername();
        User user1 = userMapper.selectUsername(username);
        if (user1 == null) {
            return null;
        }
        //密码校验
        boolean result = passwordEncoder.matches(user.getPassword(), user1.getPassword());
        if (result) {
            return user1;
        }
        return null;
    }

    @Override
    public ResultVO checkIsLogin(String uuid) {
        if (uuid == null) {
            return new ResultVO(false, "当前用户未登陆", null);
        }
        String key = new StringBuffer(RedisConstant.USER_TOKEN).append(uuid).toString();

        redisTemplate.setKeySerializer(new StringRedisSerializer());

        User user = (User) redisTemplate.opsForValue().get(key);
        if (user == null) {
            return new ResultVO(false, "当前用户未登陆", null);

        }
        redisTemplate.expire(key, 30, TimeUnit.MINUTES);

        return new ResultVO(true, "用户已登陆", user);

    }

    @Override
    public ResultVO registerUser(User user) {
        String email=user.getEmail();
        String pwd=user.getPassword();


        return null;
    }
}
