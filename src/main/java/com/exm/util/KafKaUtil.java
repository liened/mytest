package com.exm.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

@Slf4j
@Component
public class KafKaUtil {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public boolean sendMessage(String topic,String key,String message){
        ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send(topic, key, message);
        try {
            getCallback(listenableFuture);
        } catch (Exception e) {
            log.error("KafkaUtil sendMessage error",e);
        }
        return listenableFuture.isDone();
    }

    public boolean sendMessage(String topic,String message){
        ListenableFuture<SendResult<String, String>> listenerSendResult = kafkaTemplate.send(topic, message);
        try {
            getCallback(listenerSendResult);
        } catch (Exception e) {
            log.error("KafkaUtil sendMessage error",e);
        }
        return listenerSendResult.isDone();
    }

    public void getCallback(ListenableFuture<SendResult<String, String>> listenerSendResult) throws ExecutionException, InterruptedException {
        SendResult<String, String> sendResult = listenerSendResult.get();
        listenerSendResult.addCallback(SuccessCallback ->{
            log.info("kafka 发送消息成功! topic={},partition={},offset={}",sendResult.getRecordMetadata().topic(),sendResult.getRecordMetadata().partition(),
                    sendResult.getRecordMetadata().offset());
        }, FailureCallback ->{
            log.error("kafka 发送消息失败! sendResult={}",sendResult.getProducerRecord());
        });
    }

}
