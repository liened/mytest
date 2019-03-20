package com.exm.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yyx
 * @version 1.0
 * @description
 * @createDate 2019-03-13 14:10
 */
@RestController
@RequestMapping("/test")
@Api(value = "测试的",tags = {"测试的controller"})
public class TestController {
/*
    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("/t1")
    public String test(){
        String returnStr = "";
        String key = "lock_key";
        RLock lock = redissonClient.getLock(key);
        lock.lock(60, TimeUnit.SECONDS);
        String as = redisTemplate.opsForValue().get("aa");
        int a = Integer.parseInt(as);
        if (a >0){
            redisTemplate.opsForValue().set("aa",(a-1)+"");
            returnStr = Thread.currentThread().getName()+" 设置完成:"+(a-1);
        }else {
            returnStr = Thread.currentThread().getName()+"余额不足了:"+a;
        }
        lock.unlock();
        return returnStr;
    }

    @PostMapping("t2")
    public String test2(){
        String returnStr = "";
        Lock lock = new ReentrantLock();
        lock.lock();
        String as = redisTemplate.opsForValue().get("aa");
        int a = Integer.parseInt(as);
        if (a >0){
            redisTemplate.opsForValue().set("aa",(a-1)+"");
            returnStr = Thread.currentThread().getName()+" 设置完成:"+(a-1);
        }else {
            returnStr = Thread.currentThread().getName()+"余额不足了:"+a;
        }
        lock.unlock();
        return returnStr;
    }


    @PostMapping("openApi/t3")
    public String test3(){
        return "success";
    }

    */
}
