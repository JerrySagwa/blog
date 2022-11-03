package com.sagwa.blog.controller;

import com.sagwa.blog.service.ArticleService;
import com.sagwa.blog.vo.Result;
import com.sagwa.blog.vo.params.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Sagwa
 * @Date 2022/10/30 10:11
 * @ClassName ArticleController
 */
@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public Result listArticle(@RequestBody PageParam pageParam) {
        return articleService.listArticles(pageParam);
    }

    @PostMapping("/hot")
    public Result hotArticle() {
        int limit = 5;
        return articleService.hotArticle(limit);
    }

    @PostMapping("/new")
    public Result newArticle() {
        int limit = 5;
        return articleService.newArticle(limit);
    }

}
