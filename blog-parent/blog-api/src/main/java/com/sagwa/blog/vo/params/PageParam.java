package com.sagwa.blog.vo.params;

import lombok.Data;

/**
 * @Author Sagwa
 * @Date 2022/10/30 10:13
 * @ClassName PageParam
 */
@Data
public class PageParam {
    private int page = 1;
    private int pageSize = 10;
}
