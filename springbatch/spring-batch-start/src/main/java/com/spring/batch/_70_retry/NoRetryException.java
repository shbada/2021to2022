package com.spring.batch._70_retry;

public class NoRetryException extends RuntimeException {

    public NoRetryException() {
        super();
    }
    public NoRetryException(String msg) {
        super(msg);
    }
}
