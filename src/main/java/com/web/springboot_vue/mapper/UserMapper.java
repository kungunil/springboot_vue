package com.web.springboot_vue.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.web.springboot_vue.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 黄龙
 * @Date: 2021/08/13/9:02
 * @Description:
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
