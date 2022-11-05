package com.sagwa.blog.service;

import com.sagwa.blog.dao.pojo.SysUser;
import com.sagwa.blog.vo.Result;

/**
 * @Author Sagwa
 * @Date 2022/10/30 15:36
 * @ClassName SysUserService
 */
public interface SysUserService {
    SysUser findUserById(Long id);

    SysUser findUser(String account, String password);

    Result getUserInfoByToken(String token);

    SysUser finUserByAccount(String account);

    void save(SysUser user);
}
