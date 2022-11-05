package com.sagwa.blog.controller;

import com.sagwa.blog.service.LoginService;
import com.sagwa.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RelationSupport;

/**
 * @Author Sagwa
 * @Date 2022/11/5 19:37
 * @ClassName LogoutController
 */
@RestController
@RequestMapping("logout")
public class LogoutController {

    @Autowired
    private LoginService loginService;

    @GetMapping
    public Result logout(@RequestHeader("Authorization") String token) {
        return loginService.logout(token);
    }
}
