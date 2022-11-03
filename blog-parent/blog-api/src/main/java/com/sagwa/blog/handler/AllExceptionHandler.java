package com.sagwa.blog.handler;

import com.sagwa.blog.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author Sagwa
 * @Date 2022/11/1 23:19
 * @ClassName AllExceptionHandler
 */
// 对加了 @Controller 注解的方法进行拦截 AOP
@ControllerAdvice
public class AllExceptionHandler {

    // 处理 Exception.class 异常
    @ExceptionHandler(Exception.class)
    @ResponseBody // 返回 JSON 数据
    public Result doException(Exception e) {
        e.printStackTrace();
        return Result.fail(-999, "系统异常");
    }

}
