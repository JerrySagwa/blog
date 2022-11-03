package com.sagwa.blog.controller;

import com.sagwa.blog.service.TagService;
import com.sagwa.blog.vo.Result;
import com.sagwa.blog.vo.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Sagwa
 * @Date 2022/10/30 17:35
 * @ClassName TagController
 */
@RestController
@RequestMapping("tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/hot")
    public Result listHotTags() {
        int limit = 2;
        List<TagVo> tagVoList = tagService.hot(limit);
        return Result.success(tagVoList);
    }

}
