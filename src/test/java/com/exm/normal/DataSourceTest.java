package com.exm.normal;

import com.exm.module.user.User;
import com.exm.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author yyx
 * @version 1.0
 * @createDate 2018-12-12 10:15
 * @description
 */
@RunWith(SpringRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DataSourceTest {





    @Autowired
    private UserMapper userMapper;

    @Test
    public void test(){
        List<User> userList = userMapper.selectList(null);
        System.out.println("==============================================");
        System.out.println("==============================================");
        for (User u:userList){
            System.out.println(u.getId()+"-"+u.getUserName()+"-"+u.getAge()+"-"+u.getCreateTime());
        }
        System.out.println("**************************************************");
        System.out.println("**************************************************");
    }

}
