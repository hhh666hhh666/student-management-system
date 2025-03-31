package com.lzh.sms.service;

import com.lzh.sms.dto.UserLoginVO;
import com.lzh.sms.entity.User;


public interface UserService {
    User login(UserLoginVO userLoginVO);

    User register(UserLoginVO userLoginVO);
}
