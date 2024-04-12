package com.musinsa.category.exception;

public class DuplicatedCategoryException extends RuntimeException{
    public DuplicatedCategoryException(String cause){
        super(cause);
    }
}
