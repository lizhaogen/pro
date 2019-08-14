package com.qf.user.api;

import com.qf.v2.common.vo.ResultVO;
import com.qf.v2.entity.User;

public interface UserService {
    User checkLogin(User user);

    ResultVO checkIsLogin(String uuid);

    ResultVO registerUser(User user);
}
