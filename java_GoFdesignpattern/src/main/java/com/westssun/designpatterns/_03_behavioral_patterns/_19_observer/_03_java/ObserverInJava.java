package com.westssun.designpatterns._03_behavioral_patterns._19_observer._03_java;

import java.util.Observable;
import java.util.Observer;

public class ObserverInJava {

    static class User implements Observer {
        @Override
        public void update(Observable o, Object arg) { // java9 부터 deprecated
            System.out.println(arg);
        }
    }

    static class Subject extends Observable {
        public void add(String message) {
            setChanged(); // 이걸 하지않으면 알림을 받을 수가 없다.
            // 알림받는 주체의 순서는 알수가 없다.
            // 한번 변경됬을대 여러번 알림이 가야한다면 이 2줄이 세트로 반복되어야한다. 사용성이 떨어진다.
            notifyObservers(message); // 알림
        }
    }

    public static void main(String[] args) {
        Subject subject = new Subject();
        User user = new User();
        subject.addObserver(user);
        subject.add("Hello Java, Observer");
    }

}
