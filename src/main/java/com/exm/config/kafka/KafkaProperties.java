package com.exm.config.kafka;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yyx
 * @version 1.0
 * @createDate 2019-01-30 14:20
 * @description
 */
@Data
@Component
@ConfigurationProperties("spring.kafka.consumer")
public class KafkaProperties {

    /**
     * 自动偏移量重置
     */
    private String autoOffsetReset;

    /**
     * 是否开启自动提交
     */
    private boolean enableAutoCommit;

    /**
     * 自动提交的时间间隔
     */
    private Integer autoCommitInterval;

    /**
     * 消费组ID
     */
    private String groupId;

    /**
     * key反序列化类
     */
    private String keyDeserializer;

    /**
     * value反序列化类
     */
    private String valueDeserializer;

    /**
     * 一次拉去的最大记录数
     */
    private Integer maxPollRecords;
}
