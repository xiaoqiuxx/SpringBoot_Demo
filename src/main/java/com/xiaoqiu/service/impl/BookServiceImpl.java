package com.xiaoqiu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoqiu.entity.Book;
import com.xiaoqiu.mapper.BookMapper;
import com.xiaoqiu.service.BookService;
import org.springframework.stereotype.Service;

/**
 * @Description :
 * @Time : 2023/1/24 18:44
 * @Author : xiaoqiuxx
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {
}
