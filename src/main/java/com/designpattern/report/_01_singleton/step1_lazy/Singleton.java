package com.designpattern.report._01_singleton.step1_lazy;

/**
 * private 생성자와 public static 메소드를 사용하는 방법
 */
public class Singleton {
    private static Singleton instance;

    /**
     * 이 코드가 안전한가?
     * 안전하지 않다.
     * 멀티쓰레드에 안전하게 사용해야할 필요가 있다.
     * 최초 if 문을 타기전에 여러 쓰레드가 들어오면 모두 new 객체가 생성된다.
     * @return
     */
    public static Singleton getInstance() {
        // 객체가 만들어지지않았을때
        if (instance == null) {
            instance = new Singleton();
        }

        return instance;
    }

    // private 생성자를 생성한다.
    // 오직 해당 파일 안에서만 new 연산자를 사용할 수 있다.
    private Singleton() {}
}
