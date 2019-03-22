package com.exm.config.exception;

import lombok.Data;

@Data
public class BusinessException extends RuntimeException{

    private int code;
    private String message;

    public BusinessException(int code,String message){
        this.code = code;
        this.message = message;
    }

    public BusinessException(String message){
        this.code = 300;
        this.message = message;
    }
}
