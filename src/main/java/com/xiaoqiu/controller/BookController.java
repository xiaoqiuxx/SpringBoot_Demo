package com.xiaoqiu.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoqiu.config.Result;
import com.xiaoqiu.entity.Book;
import com.xiaoqiu.mapper.BookMapper;
import com.xiaoqiu.service.BookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description : 书籍信息
 * @Time : 2023/1/24 18:45
 * @Author : xiaoqiuxx
 */

@RestController
@RequestMapping("/book")
public class BookController {

    @Resource
    private BookService bookService;

    @Resource
    private BookMapper bookMapper;


    @PostMapping("/save")
    public Result<?> save(@RequestBody Book book) {
        bookService.save(book);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        QueryWrapper<Book> bookQueryWrapper = new QueryWrapper<>();
        //判断不为空
        if (StrUtil.isNotBlank(search)) {
            bookQueryWrapper.lambda().like(Book::getName, search);
        }

        Page<Book> bookPage = bookMapper.selectPage(new Page<>(pageNum, pageSize), bookQueryWrapper);
        return Result.success(bookPage);
    }

    @PutMapping("/update")
    public Result<?> update(@RequestBody Book book) {
        bookService.updateById(book);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable Long id) {
        bookService.removeById(id);
        return Result.success();
    }

}
