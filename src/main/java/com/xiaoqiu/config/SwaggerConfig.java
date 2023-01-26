package com.xiaoqiu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

/**
 * @Description :
 * @Time : 2022/12/16 15:47
 * @Author : xiaoqiuxx
 */

@Configuration
//@EnableOpenApi
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
//                .enable(false)  //关闭swagger
                .apiInfo(createApiInfo());  //指定swagger3.0版本
    }

    /**
     * 配置swagger的ApiInfo bean
     */
    @Bean
    public ApiInfo createApiInfo() {
        return new ApiInfo("xiaoqiuxx Swagger",
                "xiaoqiuxx Api Document",
                "3.0",
                "http://xiaoqiuxx.xyz",
                new Contact("xiaoqiuxx", "xiaoqiuxx.xyz", "3253530035@qq.com"),
                "Apache3.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>());
    }

}
