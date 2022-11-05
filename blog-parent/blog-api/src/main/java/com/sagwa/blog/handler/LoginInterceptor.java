package com.sagwa.blog.handler;

import com.alibaba.fastjson.JSON;
import com.sagwa.blog.dao.pojo.SysUser;
import com.sagwa.blog.service.LoginService;
import com.sagwa.blog.utils.UserHolder;
import com.sagwa.blog.vo.ErrorCode;
import com.sagwa.blog.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Sagwa
 * @Date 2022/11/5 21:14
 * @ClassName LoginInterceptor
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginService loginService;

    // 在执行 Controller 方法之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         * 1.判断请求的接口路径是不是 HandlerMethod (访问资源就不需要拦截了)
         * 2.判断 token 是否为空
         */
        if (!(handler instanceof HandlerMethod))
            // 可能是 RequestResourceHandler 访问SpringBoot 静态资源
            // classpath:static
            return true;

        String token = request.getHeader("Authorization");
        log.info("=================request start===========================");
        String requestURI = request.getRequestURI();
        log.info("request uri:{}",requestURI);
        log.info("request method:{}",request.getMethod());
        log.info("token:{}", token);
        log.info("=================request end===========================");
        SysUser user = null;
        if (StringUtils.isBlank(token) || (user = loginService.checkToken(token)) == null) {
            Result result = Result.fail(ErrorCode.TOKEN_ERROR.getCode(), ErrorCode.TOKEN_ERROR.getMsg());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }

        UserHolder.set(user);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserHolder.remove();
    }
}
