package com.exm.config;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
@EnableConfigurationProperties(RedisProperties.class)
@Slf4j
public class RedisConfig {

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory){
        return RedisCacheManager.builder(factory).build();
    }


    @Bean
    public RedisTemplate<String,Object> template(RedisConnectionFactory factory){
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        //配置连接工厂
        template.setConnectionFactory(factory);

        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        Jackson2JsonRedisSerializer jjs = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper om = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jjs.setObjectMapper(om);

        StringRedisSerializer srs = new StringRedisSerializer();
        template.setKeySerializer(srs);
        template.setValueSerializer(jjs);

        template.setHashKeySerializer(srs);
        template.setHashValueSerializer(jjs);
        template.afterPropertiesSet();
        return template;
    }
//
//    @Bean
//    public ValueOperations<String, Object> valueOperations(RedisTemplate<String, Object> redisTemplate) {
//        return redisTemplate.opsForValue();
//    }

    @Autowired
    private RedisProperties redisProperties;

    @Bean
    public RedissonClient redisson(){
        Config config = new Config();
        RedisProperties.Cluster cluster = redisProperties.getCluster();
        log.info("cluster is :{}",cluster);
        RedissonClient redissonClient = Redisson.create();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        if (!StringUtils.isEmpty(redisProperties.getUrl())){
            singleServerConfig.setAddress(redisProperties.getUrl());
        }else {
            singleServerConfig.setAddress("rediss://"+redisProperties.getHost()+":"+redisProperties.getPort());
        }
        singleServerConfig.setDatabase(redisProperties.getDatabase());
        return redissonClient;
    }
}
