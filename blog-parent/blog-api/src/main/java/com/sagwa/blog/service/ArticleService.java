package com.sagwa.blog.service;

import com.sagwa.blog.vo.Result;
import com.sagwa.blog.vo.params.PageParam;
import org.springframework.stereotype.Service;

/**
 * @Author Sagwa
 * @Date 2022/10/30 10:17
 * @ClassName ArticleService
 */
public interface ArticleService {
    Result listArticles(PageParam pageParam);

    Result hotArticle(int limit);

    Result newArticle(int limit);
}
