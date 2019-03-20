package com.exm.config.datasource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author yyx
 * @version 1.0
 * @createDate 2018-12-12 16:16
 * @description
 */
@Data
@ConfigurationProperties("spring.datasource.hikari")
public class HiKariConfigBean {

    @NestedConfigurationProperty
    private HikariProperties master;

    @NestedConfigurationProperty
    private HikariProperties slave;

}
