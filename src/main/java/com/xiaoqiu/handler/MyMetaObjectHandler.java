package com.xiaoqiu.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description :
 * @Time : 2022/12/9 10:01
 * @Author : xiaoqiuxx
 */

//添加日志注解，
@Slf4j
//放入ioc容器。
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    //插入时的填充策略
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill····");
        //MetaObjectHandler setFieldValByName(String fieldName, Object fieldVal, MetaObject metaObject)
        //第一个参数为要设置的属性 第二个参数为值 第三个值就是我们传入的元数据对象
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ····");
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
