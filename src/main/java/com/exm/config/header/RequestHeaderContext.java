package com.exm.config.header;

/**
 * @author yyx
 * @version 1.0
 * @description
 * @createDate 2019-03-14 16:14
 */
public class RequestHeaderContext {

    private static final ThreadLocal<MyHeader> headerThreadLocal = new ThreadLocal<>();

    public static void setHeader(MyHeader myHeader){
        headerThreadLocal.set(myHeader);
    }

    public static MyHeader get(){
        return headerThreadLocal.get();
    }

    public static void remove(){
        headerThreadLocal.remove();
    }
}
