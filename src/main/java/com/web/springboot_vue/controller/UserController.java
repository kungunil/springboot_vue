package com.web.springboot_vue.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.web.springboot_vue.common.api.CommonResult;
import com.web.springboot_vue.entity.User;
import com.web.springboot_vue.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 黄龙
 * @Date: 2021/08/13/8:51
 * @Description:
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping
    public CommonResult<?> save(@RequestBody User user){
        int insert = userMapper.insert(user);
        if(insert>0){
            return CommonResult.success(user);
        }else {
            return CommonResult.failed();
        }

    }

    @PutMapping
    public CommonResult<?> update(@RequestBody User user){
        int i = userMapper.updateById(user);
        if (i>0){
            return CommonResult.success(i);
        }else {
            return CommonResult.failed();
        }

    }
    @DeleteMapping("/{id}")
    public CommonResult<?> delete(@PathVariable Integer id){
        int id1 = userMapper.deleteById(id);
        if (id1>0){
            return CommonResult.success(id1);
        }else {
            return CommonResult.failed();
        }

    }

    @GetMapping
    public CommonResult<?> pageSearch(@RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "10") Integer pageSize,
                                      @RequestParam(defaultValue = "") String search){
        Page<User> userPage = userMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<User>lambdaQuery().like(User::getUsername, search));
        return CommonResult.success(userPage);
    }
}
