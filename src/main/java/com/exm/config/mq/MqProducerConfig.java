package com.exm.config.mq;

import com.exm.config.exception.BusinessException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
@Data
@Slf4j
public class MqProducerConfig {

    @Value("${rocketmq.producer.groupName}")
    private String groupName;
    @Value("${rocketmq.producer.namesrvAddr}")
    private String namesrvAddr;
    @Value("${rocketmq.producer.maxMessageSize}")
    private int maxMessageSize;
    @Value("${rocketmq.producer.sendMsgTimeout}")
    private int sendMsgTimeout;
    @Value("${rocketmq.producer.retryTimesWhenSendFailed}")
    private int retryTimesWhenSendFailed;

    @Bean
    public DefaultMQProducer defaultMQProducer(){
        if (StringUtils.isEmpty(this.groupName)){
            throw new BusinessException("MQ Producer groupName is Empty");
        }
        if (StringUtils.isEmpty(this.namesrvAddr)){
            throw new BusinessException("MQ Producer namesrvAddr is Empty");
        }
        DefaultMQProducer producer = new DefaultMQProducer(this.groupName);
        producer.setNamesrvAddr(this.namesrvAddr);
        producer.setMaxMessageSize(this.maxMessageSize);
        producer.setSendMsgTimeout(this.sendMsgTimeout);
        producer.setRetryTimesWhenSendFailed(this.retryTimesWhenSendFailed);
        try {
            producer.start();
            log.info("Producer is start! groupName:{},namesrvAddr:{}",this.groupName,this.namesrvAddr);
        } catch (MQClientException e) {
            log.error("Producer Start Error",e);
            throw new BusinessException(e.getMessage());
        }
        return producer;
    }
}
