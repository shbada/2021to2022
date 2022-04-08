package com.spring.batch.반복_오류제어.skip;

public class NoSkipException extends Exception {

    public NoSkipException() {
        super();
    }

    public NoSkipException(String msg) {
        super(msg);
    }
}
