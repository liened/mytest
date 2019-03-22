package com.exm.config;

import com.exm.config.interceptor.LoginInterceptor;
import com.exm.config.interceptor.RequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

    @Resource
    private LoginInterceptor loginInterceptor;

    @Resource
    private RequestInterceptor requestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).excludePathPatterns("/login");
        registry.addInterceptor(requestInterceptor).addPathPatterns("/openApi/**");
    }
}
