package com.example.schoolGuider.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * 每次使用swagger前,请先加上配置项
 * spring.mvc.pathmatch.matching-strategy=ant_path_matcher
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * swagger的核心配置,也是bean实例
     */
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(myApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.schoolGuider")) //配置希望被扫描的包
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))//扫描带该注解的类
                .build()
                .groupName("默认分组");
        //注意：swagger没有set方法，有get方法，部分直接以变量名的方法可以起到set的作用，如apiInfo方法起到了set方法的作用
    }

    //配置swagger信息(apiInfo)
    private ApiInfo myApiInfo() {

        //作者信息
        Contact contact = new Contact("ZhiweiXiao", "https://github.com/xzwnp", "xiaozhiwei@mail.ynu.edu.cn");

        return new ApiInfo("校园导览程序api说明",
                "主要功能如下:",
                "1.0",
                "https://www.yidongzhenlaji.cnm", contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());

    }

}
