package com.spring.batch._74_listener_retry;

public class CustomRetryException extends Exception {

    public CustomRetryException() {
        super();
    }

    public CustomRetryException(String message) {
        super(message);
    }
}
