package com.designpattern.report._01_singleton.step2_eager;

/**
 * private 생성자와 public static 메소드를 사용하는 방법
 */
public class Singleton {
    // private static Settings1 instance;

    // 멀티쓰레드 오류 방지 - 이른 초기화(eager initialization)
    // 안쓰는 객체를 미리 만들어놓는 상황이 될수도 있다. (getInstance() 호출이 없을때)
    private static final Singleton INSTANCE = new Singleton();
    public static Singleton getInstance() {
        return INSTANCE;
    }

    // private 생성자를 생성한다.
    // 오직 해당 파일 안에서만 new 연산자를 사용할 수 있다.
    private Singleton() {}
}
