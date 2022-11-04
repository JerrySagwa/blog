package com.sagwa.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sagwa.blog.dao.dos.Archives;
import com.sagwa.blog.dao.pojo.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author Sagwa
 * @Date 2022/10/30 10:03
 * @ClassName ArticleMapper
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    List<Archives> listArchives();
}
