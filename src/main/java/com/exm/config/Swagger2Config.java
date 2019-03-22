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

@EnableSwagger2
@Configuration
@EnableSwaggerBootstrapUI
public class Swagger2Config {

    @Bean
    public Docket init(){
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(true)
                .useDefaultResponseMessages(false)
                .groupName("MyTest项目")
                .apiInfo(new ApiInfoBuilder()
                    .title("我的title")
                    .description("title的描述")
                    .version("1.0")
                    .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.exm"))
                .paths(PathSelectors.any())
                .build();
    }
}
