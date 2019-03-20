package com.exm.config.datasource;

/**
 * @author yyx
 * @version 1.0
 * @createDate 2018-12-12 15:42
 * @description
 */
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
