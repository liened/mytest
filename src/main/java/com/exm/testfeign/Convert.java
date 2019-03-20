package com.exm.testfeign;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yyx
 * @version 1.0
 * @createDate 2018-12-28 16:10
 * @description
 */
public class Convert {

    public static Map obj2Map(Object obj) throws Exception{
        Map map = new HashMap();
        if(obj == null){
            return map;
        }
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field:declaredFields){
            field.setAccessible(true);
            map.put(field.getName(),field.get(obj));
        }
        return map;
    }

    public static void main(String[] args) throws Exception {
        Hxd hxd = new Hxd();
        hxd.setId(1);
        hxd.setName("aaa");
        Map map = Convert.obj2Map(hxd);
        System.out.println(map);
    }
}
