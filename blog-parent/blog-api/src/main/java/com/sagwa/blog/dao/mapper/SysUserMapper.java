package com.sagwa.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sagwa.blog.dao.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author Sagwa
 * @Date 2022/10/30 10:06
 * @ClassName SysUserMapper
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
}
