package com.exm;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MqProducerTest {

    @Autowired
    private DefaultMQProducer defaultMQProducer;

    @Test
    public void send() throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        String msg = "demo msg test";
        System.out.println("================开发发送消息:"+msg);
        Message sendMsg = new Message("myTopic","myTag",msg.getBytes());
        SendResult sendResult = defaultMQProducer.send(sendMsg);
        System.out.println("=================发送结果:"+sendResult);
    }
}
