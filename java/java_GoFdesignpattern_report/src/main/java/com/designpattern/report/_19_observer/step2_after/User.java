package com.designpattern.report._19_observer.step2_after;

/**
 * ConcreteObserver
 * ( Subscriber 구현체 )
 */
public class User implements Subscriber {

    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void handleMessage(String message) {
        System.out.println(message);
    }
}
