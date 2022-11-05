package com.sagwa.blog.controller;

import com.sagwa.blog.service.LoginService;
import com.sagwa.blog.service.SysUserService;
import com.sagwa.blog.vo.Result;
import com.sagwa.blog.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Sagwa
 * @Date 2022/11/4 23:33
 * @ClassName LoginController
 */
@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    SysUserService sysUserService;

    @Autowired
    private LoginService loginService;

    @PostMapping
    public Result login(@RequestBody LoginParam loginParam) {
        return loginService.login(loginParam);
    }


}
