package com.sagwa.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sagwa.blog.dao.mapper.SysUserMapper;
import com.sagwa.blog.dao.pojo.SysUser;
import com.sagwa.blog.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Sagwa
 * @Date 2022/10/30 15:37
 * @ClassName SysUserServiceImpl
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper userMapper;
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
}
