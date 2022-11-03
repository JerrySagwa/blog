package com.sagwa.blog.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author Sagwa
 * @Date 2022/10/30 10:26
 * @ClassName ArticleVo
 */
@Data
public class ArticleVo {

    private Long id;

    private String title;

    private String summary;

    private int commentCounts;

    private int viewCounts;

    private int weight;
    /**
     * 创建时间
     */
    private String createDate;

    private String author;

    private ArticleBodyVo body;

    private List<TagVo> tags;

    private List<CategoryVo> categorys;

}
