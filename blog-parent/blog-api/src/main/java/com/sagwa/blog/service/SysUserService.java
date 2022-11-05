package com.sagwa.blog.service;

import com.sagwa.blog.dao.pojo.SysUser;

/**
 * @Author Sagwa
 * @Date 2022/10/30 15:36
 * @ClassName SysUserService
 */
public interface SysUserService {
    SysUser findUserById(Long id);

    SysUser findUser(String account, String password);
}
