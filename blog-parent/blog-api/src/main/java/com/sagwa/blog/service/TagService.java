package com.sagwa.blog.service;

import com.sagwa.blog.vo.TagVo;

import java.util.List;

/**
 * @Author Sagwa
 * @Date 2022/10/30 15:17
 * @ClassName TagService
 */
public interface TagService {
    List<TagVo> findTagsByArticleId(Long id);

    List<TagVo> hot(int limit);
}
