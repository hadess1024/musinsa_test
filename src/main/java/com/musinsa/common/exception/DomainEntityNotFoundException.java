package com.musinsa.common.exception;

public class DomainEntityNotFoundException extends BaseException {
    public DomainEntityNotFoundException(String cause, String msg) {
        super(cause, msg);
    }
}
