package com.xiaoqiu.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * @Description :
 * @Time : 2022/12/15 17:03
 * @Author : xiaoqiuxx
 */

@Configuration
public class DruidConfig {

    //绑定配置文件  这里需要指定前缀
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }


    @Bean
    //后台监控
    public ServletRegistrationBean a() {
        //配置后台访问路径
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        //配置账号密码
        HashMap<String, String> initParameters = new HashMap<>();
        //增加配置 这个key参数名是固定的
        initParameters.put("loginUsername", "admin");
        initParameters.put("loginPassword", "123456");

        //允许谁能访问 为空代表谁都可以访问
        initParameters.put("allow", "");


        bean.setInitParameters(initParameters);  //设置初始化参数
        return bean;
    }
}
