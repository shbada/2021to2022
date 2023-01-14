package com.spring.batch._70_retry;

public class RetryableException extends RuntimeException {

    public RetryableException() {
        super();
    }

    public RetryableException(String msg) {
        super(msg);
    }
}