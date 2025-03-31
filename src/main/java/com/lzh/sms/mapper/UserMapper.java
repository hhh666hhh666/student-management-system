package com.lzh.sms.mapper;

import com.lzh.sms.dto.UserLoginVO;
import com.lzh.sms.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User getByUsername(String username);

    void insert(UserLoginVO userLoginVO);
}
