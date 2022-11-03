package com.sagwa.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sagwa.blog.dao.pojo.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author Sagwa
 * @Date 2022/10/30 10:03
 * @ClassName ArticleMapper
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

}
