package com.westssun.designpatterns._01_singleton;

/**
 * private 생성자와 public static 메소드를 사용하는 방법
 */
public class Settings1 {
    private static Settings1 instance;

    // private 생성자를 생성한다.
    // 오직 해당 파일 안에서만 new 연산자를 사용할 수 있다.
    private Settings1() {}

    /**
     * 이 코드가 안전한가?
     * 안전하지 않다.
     * 멀티쓰레드에 안전하게 사용해야할 필요가 있다.
     * @return
     */
    public static Settings1 getInstance() {
        // 객체가 만들어지지않았을때
        if (instance == null) {
            instance = new Settings1();
        }

        return instance;
    }
}
