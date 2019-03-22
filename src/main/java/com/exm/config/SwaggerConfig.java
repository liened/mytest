package com.exm.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yyx
 * @version 1.0
 * @description
 * @createDate 2019-03-20 14:11
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(true)
                .useDefaultResponseMessages(false)
                .groupName("测试系统")
                .apiInfo(new ApiInfoBuilder()
                    .title("测试系统的接口")
                    .description("这个是测试环境的接口")
                    .version("1.0")
                    .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.exm"))
                .paths(PathSelectors.any())
                .build();
    }

}
