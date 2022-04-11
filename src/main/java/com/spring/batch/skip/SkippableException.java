package com.spring.batch.skip;

public class SkippableException extends Exception {

    public SkippableException() {
        super();
    }

    public SkippableException(String msg) {
        super(msg);
    }
}
