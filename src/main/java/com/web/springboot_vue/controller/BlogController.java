package com.web.springboot_vue.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.web.springboot_vue.common.api.CommonResult;
import com.web.springboot_vue.entity.Blog;
import com.web.springboot_vue.mapper.BlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 黄龙
 * @Date: 2021/08/14/21:18
 * @Description:
 */
@RestController
@RequestMapping("/blog")
@CrossOrigin
public class BlogController {
    @Autowired
    private BlogMapper blogMapper;

    @GetMapping
    public CommonResult blogList(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "") String search
    ){

        Page<Blog> blogPage = blogMapper.selectPage(new Page<>(pageNum,pageSize), Wrappers.<Blog>lambdaQuery().like(Blog::getTitle, search));
        return CommonResult.success(blogPage);
    }

    @PostMapping
    public CommonResult blogInput(@RequestBody Blog blog){
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        int insert = blogMapper.insert(blog);
        if(insert>0) {
            return CommonResult.success(insert,"插入条数");
        }
        return CommonResult.failed();

    }

    @DeleteMapping("/{id}")
    public CommonResult deleteBlog(@PathVariable Integer id){
        int i=0;
        if(id!=null&&id>0){ i = blogMapper.deleteById(id);
        }
        if(i>0){
            return CommonResult.success(i,"删除的条数");
        }
        return CommonResult.failed();
    }
    @PutMapping
    public CommonResult editBlog(@RequestBody Blog blog){
        blog.setUpdateTime(new Date());
        int i = blogMapper.updateById(blog);
        if(i>0){
            return CommonResult.success(i,"更新条数");
        }
        return CommonResult.failed();
    }

}
