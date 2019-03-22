package com.exm;

import com.exm.business.student.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    public void set(){
        Student s = new Student();
        s.setId(1);
        s.setSName("张三");
        s.setClassId(2);
        redisTemplate.opsForValue().set("hash_1",s);
        redisTemplate.opsForValue().set("str_1","bbb");
        System.out.println("========= success =========");
    }

    @Test
    public void get(){
        Student a = (Student) redisTemplate.opsForValue().get("hash_1");
        String b = (String)redisTemplate.opsForValue().get("str_1");
        System.out.println("======================================");
        System.out.println(a);
        System.out.println(b);
        System.out.println("======================================");
    }

    @Test
    public void exper(){
        redisTemplate.opsForValue().set("a","aaa",10, TimeUnit.SECONDS);
        System.out.println("=======success=====");
    }

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void testClient(){
        String key = "tt";
        RLock lock = redissonClient.getLock(key);
        if (lock.tryLock()){
            System.out.println("is locked ...");
            boolean b = lock.delete();
            System.out.println("==============b:"+b);
        }
    }

    @Test
    public void testGet(){
        Integer aaStr = (Integer)redisTemplate.opsForValue().get("aa");
        System.out.println("------*-***************************************");
        System.out.println(aaStr);
        System.out.println("****************************************------");
    }

    @Test
    public void testChaoMai(){
        MyTask m1 = new MyTask();
       // MyTask m2 = new MyTask();
        m1.start();
        //m2.start();
    }

     class MyTask extends Thread{
        public void run(){
            for (int i=0;i<55;i++){
                RLock lock = redissonClient.getLock("aa");
                lock.lock(60,TimeUnit.SECONDS);
                Integer a = (Integer)redisTemplate.opsForValue().get("aa");
                if (a >0){
                    redisTemplate.opsForValue().set("aa",a-1);
                    System.out.println(Thread.currentThread().getName()+" 设置完成:"+a);
                }else {
                    System.out.println(Thread.currentThread().getName()+"余额不足了:"+a);
                }
                lock.unlock();
            }
        }
    }
}
