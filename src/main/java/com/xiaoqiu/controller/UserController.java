package com.xiaoqiu.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoqiu.config.Result;
import com.xiaoqiu.entity.User;
import com.xiaoqiu.mapper.UserMapper;
import com.xiaoqiu.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 用户信息控制器
 *
 * @author xiaoqiuxx
 * @since 2023-1-23
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private UserMapper userMapper;

    @PostMapping("/save")
    public Result<?> save(@RequestBody User user) {
        if (user.getPassword() == null) {
            //设置默认密码
            user.setPassword("123456");
        }
        userService.save(user);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        //判断不为空
        if (StrUtil.isNotBlank(search)) {
            userQueryWrapper.lambda().like(User::getNickName, search);
        }

        Page<User> userPage = userMapper.selectPage(new Page<>(pageNum, pageSize), userQueryWrapper);
        return Result.success(userPage);
    }

    @PutMapping("/update")
    public Result<?> update(@RequestBody User user) {
        userService.updateById(user);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable Long id) {
        userService.removeById(id);
        return Result.success();
    }

    @PostMapping("/login")
    public Result<?> login(@RequestBody User user) {
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()).eq(User::getPassword, user.getPassword()));
        if (res == null) {
            return Result.error("-1", "用户名或密码错误");
        }
        return Result.success(res);
    }

}

