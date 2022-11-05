package com.sagwa.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.sagwa.blog.dao.pojo.SysUser;
import com.sagwa.blog.service.RegisterService;
import com.sagwa.blog.service.SysUserService;
import com.sagwa.blog.utils.JWTUtils;
import com.sagwa.blog.vo.ErrorCode;
import com.sagwa.blog.vo.Result;
import com.sagwa.blog.vo.params.LoginParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Sagwa
 * @Date 2022/11/5 20:29
 * @ClassName RegisterServiceImpl
 */
@Service
@Slf4j
@Transactional
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private SysUserService userService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Override
    public Result register(LoginParam loginParam) {
        /**
         * 1.检验参数
         * 2.查找账户，账户已存在 -- 返回错误(同一个account不能重复)
         * 3.创建账户,存入数据库
         * 4.生成token
         * 5.存入Redis
         * 6.返回token
         */
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        String nickname = loginParam.getNickname();
        if (StringUtils.isBlank(account)
                || StringUtils.isBlank(password)
                || StringUtils.isBlank(nickname)
        ){
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(),ErrorCode.PARAMS_ERROR.getMsg());
        }
        SysUser user = userService.finUserByAccount(account);
        if (user != null) {
            return Result.fail(ErrorCode.ACCOUNT_EXIST.getCode(), ErrorCode.ACCOUNT_EXIST.getMsg());
        }

        user = createDefaultUser(account, password, nickname);
        userService.save(user);
        log.debug("userId = {}", user.getId());
        String token = JWTUtils.createToken(user.getId());
        redisTemplate.opsForValue().set("TOKEN:" + token, JSON.toJSONString(user));

        return Result.success(token);
    }

    private SysUser createDefaultUser(String account, String password, String nickname) {
        SysUser user;
        user = new SysUser();
        user.setNickname(nickname);
        user.setAccount(account);
        user.setPassword(DigestUtils.md5Hex(password + LoginServiceImpl.SALT));
        user.setCreateDate(System.currentTimeMillis());
        user.setLastLogin(System.currentTimeMillis());
        user.setAvatar("/static/img/logo.b3a48c0.png");
        user.setAdmin(1); //1 为true
        user.setDeleted(0); // 0 为false
        user.setSalt("");
        user.setStatus("");
        user.setEmail("");
        return user;
    }
}
