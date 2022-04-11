package com.spring.batch.skip;

public class NoSkipException extends Exception {

    public NoSkipException() {
        super();
    }

    public NoSkipException(String msg) {
        super(msg);
    }
}
