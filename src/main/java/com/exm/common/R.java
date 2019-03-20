package com.exm.common;

import lombok.Data;

/**
 * @author yyx
 * @version 1.0
 * @createDate 2018-12-12 20:00
 * @description   自定义返回的实体类
 */
@Data
public class R<T> {

    private int status;
    private String message;
    private Object data;

    public R(int status,String message,Object data){
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> R success(){
        return new R(REnum.Success.getCode(),"操作成功",null);
    }

    public static <T> R successWithData(Object data){
        return new R(REnum.Success.getCode(),"",data);
    }

    public static <T> R failure(REnum rEnum){
        return new R(rEnum.getCode(),rEnum.getDesc(),null);
    }
    public static <T> R failureWithDetail(REnum rEnum, String message){
        return new R(rEnum.getCode(),message,null);
    }
    public static <T> R failure(String message){
        return new R(REnum.System_Error.getCode(),message,null);
    }
}
