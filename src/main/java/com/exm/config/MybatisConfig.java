package com.exm.config;

/**
 * @author yyx
 * @version 1.0
 * @description
 * @createDate 2019-03-20 15:09
 */
//@Configuration
//@EnableTransactionManagement
//@MapperScan("com.exm.mapper")
public class MybatisConfig {
//
//    @Bean(name = "shardSqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactoryBean(@Qualifier(value = "shardingDataSource")DataSource dataSource) throws Exception{
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        return bean.getObject();
//    }
//
//    @Bean(name="shardTransactionManager")
//    public DataSourceTransactionManager manager(@Qualifier(value = "shardingDataSource")DataSource dataSource){
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    public SqlSessionTemplate template(@Qualifier(value = "shardSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
}
