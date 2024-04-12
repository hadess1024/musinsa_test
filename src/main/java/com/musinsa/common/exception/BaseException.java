package com.musinsa.common.exception;


import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{

    private String causeValue;

    public BaseException(String cause, String msg){
        super(msg);
        this.causeValue=cause;
    }
}
