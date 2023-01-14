package com.westssun.designpatterns._03_behavioral_patterns._23_visitor._01_before;

public class Client {

    public static void main(String[] args) {
        Shape rectangle = new Rectangle();

        // Device 타입으로 넘길 수 있다.
//        rectangle.printTo(new Phone());

        Device device = new Phone();
        rectangle.printTo(device);
    }
}
