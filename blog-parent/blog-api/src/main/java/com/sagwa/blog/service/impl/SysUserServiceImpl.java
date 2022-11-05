package com.sagwa.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sagwa.blog.dao.mapper.SysUserMapper;
import com.sagwa.blog.dao.pojo.SysUser;
import com.sagwa.blog.service.SysUserService;
import com.sagwa.blog.utils.JWTUtils;
import com.sagwa.blog.vo.ErrorCode;
import com.sagwa.blog.vo.LoginUserVo;
import com.sagwa.blog.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author Sagwa
 * @Date 2022/10/30 15:37
 * @ClassName SysUserServiceImpl
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Override
    public SysUser findUserById(Long id) {
        SysUser user = userMapper.selectById(id);
        if (user == null) {
            // 查询为空，使用默认名
            user = new SysUser();
            user.setNickname("sagwa");
        }
        return user;
    }

    @Override
    public SysUser findUser(String account, String password) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getAccount, account)
                    .eq(SysUser::getPassword, password)
                .select(SysUser::getId,SysUser::getAccount,SysUser::getAvatar,SysUser::getNickname)
                .last("limit 1");
        SysUser user = userMapper.selectOne(queryWrapper);

        return user;
    }

    @Override
    public Result getUserInfoByToken(String token) {
        Map<String, Object> map = JWTUtils.checkToken(token);
        if (map == null) {
            return Result.fail(ErrorCode.NO_LOGIN.getCode(), ErrorCode.NO_LOGIN.getMsg());
        }
        String s = redisTemplate.opsForValue().get("TOKEN:" + token);
        if (StringUtils.isBlank(s)) {
            return Result.fail(ErrorCode.NO_LOGIN.getCode(), ErrorCode.NO_LOGIN.getMsg());
        }
        SysUser user = JSON.parseObject(s, SysUser.class);

        return Result.success(sysUser2LoginUserVo(user));
    }

    @Override
    public SysUser finUserByAccount(String account) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getAccount, account)
                .last("limit 1");
        SysUser user = userMapper.selectOne(queryWrapper);
        return user;
    }

    @Override
    public void save(SysUser user) {
        //注意 默认生成的id 是分布式id 采用了雪花算法
        userMapper.insert(user);
    }

    private LoginUserVo sysUser2LoginUserVo(SysUser user) {
        LoginUserVo loginUserVo = new LoginUserVo();
        BeanUtils.copyProperties(user, loginUserVo);
        return loginUserVo;
    }
}
