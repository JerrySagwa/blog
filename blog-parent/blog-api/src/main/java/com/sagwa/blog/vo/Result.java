package com.sagwa.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author Sagwa
 * @Date 2022/10/30 10:13
 * @ClassName Result
 */
@Data
@AllArgsConstructor
public class Result {
    private boolean success;
    private Integer code;
    private String msg;
    private Object data;

    public static Result success(Object data) {
        return new Result(true, 200, "success", data);
    }

    public static  Result fail(Integer code, String msg) {
        return new Result(false, code, msg, null);
    }
}
