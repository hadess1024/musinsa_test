package com.musinsa.product.exception;

public class DuplicatedProductException extends RuntimeException{
    public DuplicatedProductException(String cause){
        super(cause);
    }
}
