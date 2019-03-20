package com.exm.common;

/**
 * @author yyx
 * @version 1.0
 * @description
 * @createDate 2019-03-14 17:25
 */
public enum REnum {

    Success(200,"成功"),
    Runtime_Error(201,"运行时错误"),
    System_Error(500,"系统错误"),
    Token_Not_Exist(501,"token不存在"),
    Token_Error(502,"token验证错误"),
    User_Not_Exist(1,"用户不存在"),
    PWD_Error(2,"密码不正确"),
    ;

    private int code;
    private String desc;
    REnum(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return code;
    }

    public String getDesc(){
        return desc;
    }
}
