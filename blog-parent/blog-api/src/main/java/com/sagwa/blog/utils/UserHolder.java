package com.sagwa.blog.utils;

import com.sagwa.blog.dao.pojo.SysUser;

/**
 * @Author Sagwa
 * @Date 2022/11/5 22:07
 * @ClassName UserHolder
 */
public class UserHolder {
    public UserHolder() {
    }

    private final static ThreadLocal<SysUser> LOCAL = new ThreadLocal<>();

    public static void set(SysUser user) {
        LOCAL.set(user);
    }

    public static void remove() {
        LOCAL.remove();
    }

    public static SysUser get() {
        return LOCAL.get();
    }
}
