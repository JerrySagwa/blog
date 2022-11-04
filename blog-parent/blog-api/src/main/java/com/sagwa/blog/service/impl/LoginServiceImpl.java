package com.sagwa.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.sagwa.blog.dao.pojo.SysUser;
import com.sagwa.blog.service.LoginService;
import com.sagwa.blog.service.SysUserService;
import com.sagwa.blog.utils.JWTUtils;
import com.sagwa.blog.vo.ErrorCode;
import com.sagwa.blog.vo.Result;
import com.sagwa.blog.vo.params.LoginParam;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author Sagwa
 * @Date 2022/11/4 23:35
 * @ClassName LoginServiceImpl
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SysUserService userService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private final static String SALT = "mszlu!@#";

    @Override
    public Result login(LoginParam loginParam) {
        /**
         * 1.登录参数验证
         * 2.查询数据库(密码加密)
         * 3.判断用户是否存在
         * 4.生成 Token 设置过期时间
         * 5.Token 存入 Redis
         * 6.返回 Token 给客户端
         */
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        // 加密
        password = DigestUtils.md5Hex(password + SALT);
        SysUser user = userService.findUser(account, password);
        if (user == null) {
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(), ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        String token = JWTUtils.createToken(user.getId());
        redisTemplate.opsForValue().set("TOKEN:" + token, JSON.toJSONString(user), 1, TimeUnit.DAYS);

        return Result.success(token);
    }
}
