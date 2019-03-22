package com.exm.common;

/**
 * @author yyx
 * @version 1.0
 * @description
 * @createDate 2019-03-20 14:29
 */
public enum REnum {

    Success("200","操作成功"),
    System_Error("500","系统错误"),
    Operate_Fail("300","操作失败"),
    ;

    private String code;
    private String message;

    REnum(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode(){
        return code;
    }
    public String getMessage(){
        return message;
    }
}
