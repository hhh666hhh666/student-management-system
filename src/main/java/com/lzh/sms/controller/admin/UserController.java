package com.lzh.sms.controller.admin;

import com.lzh.sms.result.Result;
import com.lzh.sms.constant.JwtClaimsConstant;
import com.lzh.sms.dto.UserLoginVO;
import com.lzh.sms.entity.User;
import com.lzh.sms.properties.JwtProperties;
import com.lzh.sms.service.UserService;
import com.lzh.sms.utils.JwtUtil;
import com.lzh.sms.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/admin/user")
@RestController
@Api(tags = "管理员登录相关接口")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/login")
    @ApiOperation("登录")
    public Result<UserVO> login(@RequestBody UserLoginVO userLoginVO) {

        User user = userService.login(userLoginVO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        UserVO userVO = UserVO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .token(token)
                .build();

        return Result.success(userVO);
    }

    @PostMapping("register")
    @ApiOperation("注册")
    public Result<UserVO> register(@RequestBody UserLoginVO userLoginVO) {
        User user = userService.register(userLoginVO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        UserVO userVO = UserVO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .token(token)
                .build();

        return Result.success(userVO);
    }
}
