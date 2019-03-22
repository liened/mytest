package com.exm.common;

import lombok.Data;

/**
 * @author yyx
 * @version 1.0
 * @description
 * @createDate 2019-03-20 14:24
 */
@Data
public class R<T> {

    private String code;
    private String message;
    private T data;

    private R(String code,String message,T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public static <T> R successWithData(T data){
        return new R(REnum.Success.getCode(),"",data);
    }

    public static <T> R success(){
        return new R(REnum.Success.getCode(),"",null);
    }

    public static <T> R fail(REnum renum){
        return new R(renum.getCode(),renum.getCode(),null);
    }

    public static <T> R fail(String message){
        return new R(REnum.System_Error.getCode(),message,null);
    }

    public static <T> R failWithDetail(REnum renum, String message){
        return new R(renum.getCode(),message,null);
    }

}
