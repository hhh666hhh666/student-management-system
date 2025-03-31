package com.lzh.sms.service.impl;

import com.lzh.sms.constant.MessageConstant;
import com.lzh.sms.dto.UserLoginVO;
import com.lzh.sms.entity.User;
import com.lzh.sms.exception.AccountNotFoundException;
import com.lzh.sms.exception.PasswordErrorException;
import com.lzh.sms.mapper.UserMapper;
import com.lzh.sms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(UserLoginVO userLoginVO){
        String username = userLoginVO.getUsername();
        String password = userLoginVO.getPassword();

        //1、根据用户名查询数据库中的数据
        User user = userMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (user == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        // 对前端传过来的明文密码进行md5加密
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(user.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        //3、返回实体对象
        return user;
    }

    @Override
    public User register(UserLoginVO userLoginVO) {
        String username = userLoginVO.getUsername();
        String password = userLoginVO.getPassword();

        //1、根据用户名查询数据库中的数据
        User user = userMapper.getByUsername(username);

        //2、处理各种异常情况（用户名已存在）
        if (user != null) {
            //账号已存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_EXIST);
        }

        // 对前端传过来的明文密码进行md5加密
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        userLoginVO.setPassword(password);

        //3、返回实体对象
        userMapper.insert(userLoginVO);
        return userMapper.getByUsername(username);
    }
}
