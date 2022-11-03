package com.sagwa.blog.dao.pojo;

import lombok.Data;

/**
 * @Author Sagwa
 * @Date 2022/10/30 10:06
 * @ClassName SysUser
 */
@Data
public class SysUser {

    private Long id;

    private String account;

    private Integer admin;

    private String avatar;

    private Long createDate;

    private Integer deleted;

    private String email;

    private Long lastLogin;

    private String mobilePhoneNumber;

    private String nickname;

    private String password;

    private String salt;

    private String status;
}

