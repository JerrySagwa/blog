package com.sagwa.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sagwa.blog.dao.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author Sagwa
 * @Date 2022/10/30 10:07
 * @ClassName TagMapper
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

    // 根据文章 ID 查询标签列表
    List<Tag> findTagsByArticleId(long articleId);

    List<Long> findHotTagIds(int limit);

    List<Tag> findTagsByTagIds(List<Long> ids);
}
