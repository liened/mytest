package com.exm.config;

//import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
//import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
//import com.baomidou.mybatisplus.plugins.SqlExplainInterceptor;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MybatisPlusConfig {

    /**
     * 分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

    /**
     * 输出每条SQL语句及其执行时间，生产环境不建议使用该插件
     * @return
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor interceptor = new PerformanceInterceptor();
        //格式化SQL语句
        interceptor.setFormat(true);
        //sql执行时间超过value值就会停止执行，单位是毫秒
        interceptor.setMaxTime(300000);
        return interceptor;
    }

    /**
     *  3.0.6 SqlExplainInterceptor
     *  如果是对全表的删除或更新操作，就会终止该操作
     *  这个插件配置了一个属性，stopProceed设置为true后，如果执行的是删除表中全部内容，那就会抛出异常，终止该操作。该插件主要是防止手抖误删数据。
     *  @apiNote 没有生效，应该是数据库版本的问题,mysql要5.6.3以上才可以
     * @return
     */
//    @Bean
//    public SqlExplainInterceptor sqlExplainInterceptor(){
//        SqlExplainInterceptor interceptor = new SqlExplainInterceptor();
//        interceptor.setStopProceed(true);
//        return interceptor;
//    }

    /**
     * http://www.cnblogs.com/liuyangfirst/p/9744011.html
     * 2.0在配置文件指定，3.x使用Bean指定,3.x mapper的配置也变了，放到db-config下了
     * @return
     */
    @Bean
    public ISqlInjector logicSqlInjector(){
        return new LogicSqlInjector();
    }

}
