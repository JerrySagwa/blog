package com.sagwa.blog.service;

import com.sagwa.blog.vo.Result;
import com.sagwa.blog.vo.params.LoginParam;

/**
 * @Author Sagwa
 * @Date 2022/11/5 20:28
 * @ClassName RegisterService
 */
public interface RegisterService {
    Result register(LoginParam loginParam);
}
