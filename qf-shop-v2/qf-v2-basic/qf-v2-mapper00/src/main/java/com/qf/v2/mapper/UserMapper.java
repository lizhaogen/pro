package com.qf.v2.mapper;

import com.qf.v2.common.base.BaseDao;
import com.qf.v2.entity.User;

public interface UserMapper extends BaseDao<User> {

    User selectUsername(String username);
    User registerUser(User user);
}