package com.musinsa.brand.exception;

public class DuplicatedBrandException extends RuntimeException{
    public DuplicatedBrandException(String cause){
        super(cause);
    }
}
