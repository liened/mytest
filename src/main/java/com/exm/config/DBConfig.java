package com.exm.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import io.shardingsphere.api.config.rule.ShardingRuleConfiguration;
import io.shardingsphere.api.config.rule.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@AutoConfigureAfter(DataSource.class)
public class DBConfig {

    private final DataSource dataSource;
    private DataSource shardingDataSource;

    public DBConfig(javax.sql.DataSource dataSource){
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void shardingDataSource() throws SQLException{
        ShardingRuleConfiguration configuration = new ShardingRuleConfiguration();
        //分表策略
        configuration.getTableRuleConfigs().add(tableRuleConfiguration());
        //绑定表规则列表
        this.shardingDataSource = ShardingDataSourceFactory.createDataSource(createDSMap(),configuration,new HashMap<>(1),new Properties());
    }

    //分表策略
    private TableRuleConfiguration tableRuleConfiguration(){
        TableRuleConfiguration rule = new TableRuleConfiguration();
        //逻辑表名称
        rule.setLogicTable("t_order");
        //源名+表名
        rule.setActualDataNodes("ds0.t_order_$->{0..1}");
        //表分片策略
        StandardShardingStrategyConfiguration strategyConfiguration = new StandardShardingStrategyConfiguration("user_id",new DemoTableShardingAlgorithm());
        rule.setTableShardingStrategyConfig(strategyConfiguration);
        //自增列名称
        rule.setKeyGeneratorColumnName("id");
        return rule;
    }

    private Map<String,DataSource> createDSMap(){
        Map<String,DataSource> dsMap = new HashMap<>(2);
        dsMap.put("ds0",dataSource);
        return dsMap;
    }

    //设置数据源为sharding jdbc
    @Bean
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean(){
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(this.shardingDataSource);
        return bean;
    }

/*
    @Bean(name = "mydb")
    @Qualifier("mydb")
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource dataSource(){
        return DruidDataSourceBuilder.create().build();
    }


     // 配置分库分表策略
    @Bean(name = "shardingDataSource")
    public DataSource shardingDS(@Qualifier(value = "mydb") DataSource ds) throws Exception {
        ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();
        shardingRuleConfiguration.getTableRuleConfigs().add(getTableRuleConfiguration());
        shardingRuleConfiguration.getBindingTableGroups().add("t_order");
        shardingRuleConfiguration.setDefaultDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_id",new DemoDBShardingAlgorithm()));
        shardingRuleConfiguration.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_id",new DemoTableShardingAlgorithm()));
        Map<String,DataSource> dbMap = new HashMap<>(2);
        dbMap.put("ds_0",ds);
        Properties properties = new Properties();
        return ShardingDataSourceFactory.createDataSource(dbMap,shardingRuleConfiguration,new HashMap<String,Object>(),properties);
    }

    //设置表的node
    @Bean
    public TableRuleConfiguration getTableRuleConfiguration(){
        TableRuleConfiguration configuration = new TableRuleConfiguration();
        configuration.setLogicTable("t_order");
        configuration.setActualDataNodes("ds_0.t_order_${0..1}");
        configuration.setKeyGeneratorColumnName("user_id");
        return configuration;
    }

    */
}
