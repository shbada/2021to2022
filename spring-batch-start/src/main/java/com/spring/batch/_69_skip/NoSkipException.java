package com.spring.batch._69_skip;

public class NoSkipException extends Exception {

    public NoSkipException() {
        super();
    }

    public NoSkipException(String msg) {
        super(msg);
    }
}
