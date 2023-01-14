package com.westssun.designpatterns._03_behavioral_patterns._19_observer._03_java;

public class MyEvent {

    private String message;

    public MyEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
