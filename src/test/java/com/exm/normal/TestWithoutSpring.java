package com.exm.normal;

import com.alibaba.druid.support.json.JSONUtils;
import com.exm.entity.User;
import org.junit.Test;

import java.awt.Desktop;
import java.util.Arrays;
import java.util.LinkedHashMap;

/**
 * @author yyx
 * @version 1.0
 * @createDate 2018-12-11 15:19
 * @description
 */
public class TestWithoutSpring {

    @Test
    public void charTest(){
       String i = "a";
        char[] a = i.toCharArray();
        System.out.println(Arrays.toString(a));
    }

    @Test
    public void deskTop(){
        System.out.println(Desktop.isDesktopSupported());
    }

    @Test
    public void test(){
        LinkedHashMap<String,String> map = new LinkedHashMap(2);
        map.put("a","aa");
        map.put("b","bb");
        map.put("c","cc");
    }

    @Test
    public void t(){
        User user = new User();
        user.setAge(12);
        String s = JSONUtils.toJSONString(user);
        System.out.println(s);
    }

}
