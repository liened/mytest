package com.exm.common;

/**
 * @author yyx
 * @version 1.0
 * @description
 * @createDate 2019-03-14 17:59
 */
public class UserSession {

    private static ThreadLocal<Long> local = new ThreadLocal<>();

    public static void setUserId(long userId){
        local.set(userId);
    }

    public static long getUserId(){
        return local.get();
    }

    public static void remove(){
        local.remove();
    }
}
