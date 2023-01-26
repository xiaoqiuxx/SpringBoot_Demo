package com.xiaoqiu;

//import org.mybatis.spring.annotation.MapperScan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @Description :
 * @Time : 2022/12/8 18:50
 * @Author : xiaoqiuxx
 */

@EnableOpenApi
@SpringBootApplication
@MapperScan("com.xiaoqiu.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
