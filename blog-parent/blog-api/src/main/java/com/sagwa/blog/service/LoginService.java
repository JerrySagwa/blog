package com.sagwa.blog.service;

import com.sagwa.blog.vo.Result;
import com.sagwa.blog.vo.params.LoginParam;

/**
 * @Author Sagwa
 * @Date 2022/11/4 23:33
 * @ClassName LoginService
 */
public interface LoginService {
    Result login(LoginParam loginParam);
}
