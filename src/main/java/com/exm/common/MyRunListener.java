package com.exm.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyRunListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
//        if (event.getApplicationContext().getParent().getParent() == null) {
//            log.info("========================== 【MyTest System Start】 ========================");
//        }
        log.info("====================这是服务启动完成后的一个任务==============");
    }
}
