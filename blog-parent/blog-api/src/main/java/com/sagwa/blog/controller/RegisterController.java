package com.sagwa.blog.controller;

import com.sagwa.blog.service.RegisterService;
import com.sagwa.blog.vo.Result;
import com.sagwa.blog.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Sagwa
 * @Date 2022/11/5 20:25
 * @ClassName RegisterController
 */
@RestController
@RequestMapping("register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping
    public Result register(@RequestBody LoginParam loginParam) {
        return registerService.register(loginParam);
    }

}
