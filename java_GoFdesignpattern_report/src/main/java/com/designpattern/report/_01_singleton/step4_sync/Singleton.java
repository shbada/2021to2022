package com.designpattern.report._01_singleton.step4_sync;

/**
 * private 생성자와 public static 메소드를 사용하는 방법
 */
public class Singleton {
    private static Singleton INSTANCE;

    // private 생성자를 생성한다.
    // 오직 해당 파일 안에서만 new 연산자를 사용할 수 있다.
    private Singleton() {}

    // 동기 synchronized 처리
    // Lock 을 잡아서 Lock 을 가진 쓰레드만 접근가능하도록 하는 메커니즘이 필요하기 때문에 성능 이슈가 생길 수도 있다.
    public static synchronized Singleton getInstance() {
        // 객체가 만들어지지않았을때
        if (INSTANCE == null) {
            INSTANCE = new Singleton();
        }

        return INSTANCE;
    }
}
