package com.westssun.designpatterns._01_creational_patterns._01_singleton.serialize;

import java.io.Serializable;

public class Settings1Serialize implements Serializable {
    private static Settings1Serialize instance;

    // private 생성자를 생성한다.
    // 오직 해당 파일 안에서만 new 연산자를 사용할 수 있다.
    private Settings1Serialize() {}

    /**
     * 이 코드가 안전한가?
     * 안전하지 않다.
     * 멀티쓰레드에 안전하게 사용해야할 필요가 있다.
     * @return
     */
    public static Settings1Serialize getInstance() {
        // 객체가 만들어지지않았을때
        if (instance == null) {
            instance = new Settings1Serialize();
        }

        return instance;
    }

    protected Object readResolve() {
        return getInstance();
    }
}
