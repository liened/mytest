package com.exm.config.datasource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@Data
@ConfigurationProperties("spring.datasource.hikari")
public class HiKariConfigBean {

    @NestedConfigurationProperty
    private HikariProperties master;

    @NestedConfigurationProperty
    private HikariProperties slave;

}
