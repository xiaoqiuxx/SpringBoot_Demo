package com.xiaoqiu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description :
 * @Time : 2023/1/24 18:39
 * @Author : xiaoqiuxx
 */

@TableName("book")
@Data
public class Book {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String author;
    private BigDecimal price;
    @JsonFormat(pattern = "yyyy-MM-DD", timezone = "GMT+8")
    private Date createTime;
    private String cover;
}
