package com.exm.config.datasource;

import com.exm.util.ReflectionUtil;
import com.zaxxer.hikari.HikariConfig;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.stream.Stream;

/**
 * @author yyx
 * @version 1.0
 * @createDate 2018-12-12 16:17
 * @description
 */
@Data
public class HikariProperties {

    private String jdbcUrl;
    //    private String dataSourceClassName;
    private String username;
    private String password;
    private boolean autoCommit;
    private long connectionTimeout;
    private long idleTimeout;
    private long maxLifetime;
    private String connectionTestQuery;
    private int minimumIdle;
    private int maximumPoolSize;
    private String poolName; // Default: auto-generated

    public static <T> HikariConfig hikariConfig(T t){
        HikariConfig hikariConfig = new HikariConfig();
        if (t != null){
            Field[] fields = t.getClass().getDeclaredFields();
            Stream.of(fields).forEach(field -> ReflectionUtil.invokeSetter(hikariConfig,field.getName(),ReflectionUtil.invokeGetter(t, field.getName())));
        }
        return hikariConfig;
    }
}
