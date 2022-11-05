package com.sagwa.blog.vo;

import lombok.Data;

/**
 * @Author Sagwa
 * @Date 2022/11/5 19:26
 * @ClassName LoginVo
 */
@Data
public class LoginUserVo {
    private Long id;

    private String account;

    private String nickname;

    private String avatar;
}
