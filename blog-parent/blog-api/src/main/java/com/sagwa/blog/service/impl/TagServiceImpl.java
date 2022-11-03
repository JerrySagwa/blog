package com.sagwa.blog.service.impl;

import com.sagwa.blog.dao.mapper.TagMapper;
import com.sagwa.blog.dao.pojo.Tag;
import com.sagwa.blog.service.TagService;
import com.sagwa.blog.vo.TagVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author Sagwa
 * @Date 2022/10/30 15:18
 * @ClassName TagServiceImpl
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<TagVo> findTagsByArticleId(Long id) {
        // mybatis plus 无法进行多表查询
        List<Tag> tags = tagMapper.findTagsByArticleId(id);
        return copyList(tags);
    }

    public TagVo copy(Tag tag){
        TagVo tagVo = new TagVo();
        BeanUtils.copyProperties(tag,tagVo);
        return tagVo;
    }
    public List<TagVo> copyList(List<Tag> tagList){
        List<TagVo> tagVoList = new ArrayList<>();
        for (Tag tag : tagList) {
            tagVoList.add(copy(tag));
        }
        return tagVoList;
    }

    @Override
    public List<TagVo> hot(int limit) {
        List<Long> ids = tagMapper.findHotTagIds(limit);
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }

        List<Tag> tags = tagMapper.findTagsByTagIds(ids);
        return copyList(tags);
    }
}
