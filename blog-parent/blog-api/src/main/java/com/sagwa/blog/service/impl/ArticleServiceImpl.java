package com.sagwa.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sagwa.blog.dao.dos.Archives;
import com.sagwa.blog.dao.mapper.ArticleMapper;
import com.sagwa.blog.dao.mapper.TagMapper;
import com.sagwa.blog.dao.pojo.Article;
import com.sagwa.blog.dao.pojo.SysUser;
import com.sagwa.blog.service.ArticleService;
import com.sagwa.blog.service.SysUserService;
import com.sagwa.blog.service.TagService;
import com.sagwa.blog.vo.ArticleVo;
import com.sagwa.blog.vo.Result;
import com.sagwa.blog.vo.params.PageParam;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Sagwa
 * @Date 2022/10/30 10:18
 * @ClassName ArticleServiceImpl
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private TagService tagService;
    @Autowired
    private SysUserService userService;

    @Override
    public Result hotArticle(int limit) {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        // 按照文章阅读数排序
        wrapper.orderByDesc(Article::getViewCounts);
        wrapper.select(Article::getId, Article::getTitle);
        wrapper.last("limit " + limit);
        List<Article> articles = articleMapper.selectList(wrapper);
        /**
         * select id, title from ms_article order by view_counts
         */
        return Result.success(copyList(articles, false, false));
    }

    @Override
    public Result newArticle(int limit) {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        // 按照文章阅读数排序
        wrapper.orderByDesc(Article::getCreateDate);
        wrapper.select(Article::getId, Article::getTitle);
        wrapper.last("limit " + limit);
        List<Article> articles = articleMapper.selectList(wrapper);
        return Result.success(copyList(articles, false, false));
    }

    @Override
    public Result listArchives() {
        List<Archives> archives = articleMapper.listArchives();
        return Result.success(archives);
    }

    @Override
    public Result listArticles(PageParam pageParam) {
        /**
         * 分页查询 article 数据库表，得到结果
         */
        Page<Article> page = new Page<>(pageParam.getPage(), pageParam.getPageSize());
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 是否置顶
        // 按照创建时间倒序排
        queryWrapper.orderByDesc(Article::getCreateDate, Article::getWeight);
        Page<Article> articlePage = articleMapper.selectPage(page, queryWrapper);
        // dao --> vo
        List<Article> records = articlePage.getRecords();
        List<ArticleVo> articleVoList = copyList(records,true, true);
        return Result.success(articleVoList);
    }

    private List<ArticleVo> copyList(List<Article> records, boolean isTag, boolean isAuthor) {
        List<ArticleVo> articleVos = records.stream()
                .map(article -> copy(article, isTag, isAuthor))
                .collect(Collectors.toList());
        return articleVos;
    }

    private ArticleVo copy(Article article, boolean isTag, boolean isAuthor) {
        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article, articleVo);
        articleVo.setCreateDate(new DateTime(article.getCreateDate()).toString("yyyy-MM-dd HH:mm"));
        // 不是所有的接口都需要标签和作者信息
        if (isTag) {
            articleVo.setTags(tagService.findTagsByArticleId(article.getId()));
        }

        if (isAuthor) {
            Long authorId = article.getAuthorId();
            SysUser user = userService.findUserById(authorId);
            articleVo.setAuthor(user.getNickname());
        }
        return articleVo;
    }
}
