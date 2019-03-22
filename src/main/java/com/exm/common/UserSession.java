package com.exm.common;

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
