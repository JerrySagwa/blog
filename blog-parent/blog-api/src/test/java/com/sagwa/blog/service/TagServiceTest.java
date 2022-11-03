package com.sagwa.blog.service;

import com.sagwa.blog.vo.TagVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author Sagwa
 * @Date 2022/10/30 16:34
 * @ClassName TagServiceTest
 */
@SpringBootTest
public class TagServiceTest {

    @Autowired
    private TagService tagService;

    @Test
    public void testTag() {
        List<TagVo> tagsByArticleId = tagService.findTagsByArticleId(1L);
    }

}
