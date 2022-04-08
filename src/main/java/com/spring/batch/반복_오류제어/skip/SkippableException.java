package com.spring.batch.반복_오류제어.skip;

public class SkippableException extends Exception {

    public SkippableException() {
        super();
    }

    public SkippableException(String msg) {
        super(msg);
    }
}
