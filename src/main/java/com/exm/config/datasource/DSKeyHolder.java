package com.exm.config.datasource;

public class DSKeyHolder {

    private static final ThreadLocal<String> dsContext = new ThreadLocal<>();

    public static String get(){
        return dsContext.get();
    }

    public static void set(String dsName){
        dsContext.set(dsName);
    }

    public static void clean(){
        dsContext.remove();
    }
}
