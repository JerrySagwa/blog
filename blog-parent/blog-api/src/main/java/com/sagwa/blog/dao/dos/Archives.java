package com.sagwa.blog.dao.dos;

import lombok.Builder;
import lombok.Data;

/**
 * @Author Sagwa
 * @Date 2022/11/4 21:28
 * @ClassName Archives
 */
@Data
@Builder
public class Archives {
    private Integer year;

    private Integer month;

    private Integer count;

}
