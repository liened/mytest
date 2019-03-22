package com.exm.config.header;

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
