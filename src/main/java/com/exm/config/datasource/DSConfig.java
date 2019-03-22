package com.exm.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(HiKariConfigBean.class)
public class DSConfig {

    @Autowired
    private HiKariConfigBean hiKariConfigBean;

    @Bean
    public DataSource dataSource(){
        RoutingDS routingDataSource = new RoutingDS();
        Map<Object,Object> targetDSMap = new HashMap(2);
        targetDSMap.put(DSType.Master,new HikariDataSource(HikariProperties.hikariConfig(hiKariConfigBean.getMaster())));
        targetDSMap.put(DSType.Slave,new HikariDataSource(HikariProperties.hikariConfig(hiKariConfigBean.getSlave())));
        routingDataSource.setTargetDataSources(targetDSMap);
        routingDataSource.setDefaultTargetDataSource(targetDSMap.get(DSType.Master));
        return routingDataSource;
    }

    /**
     * 测试不加这个也可以
     * @return
     */
    @Bean(name = "trsm")
    public PlatformTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }
}
