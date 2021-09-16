package com.web.springboot_vue.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.web.springboot_vue.entity.Blog;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 黄龙
 * @Date: 2021/08/14/21:17
 * @Description:
 */
@Mapper
public interface BlogMapper extends BaseMapper<Blog> {

}
