package com.westssun.designpatterns._01_singleton;

/**
 * private 생성자와 public static 메소드를 사용하는 방법
 */
public class Settings1 {
    // private static Settings1 instance;

    // 멀티쓰레드 오류 방지 - 이른 초기화(eager initialization)
    // 안쓰는 객체를 미리 만들어놓는 상황이 될수도 있다. (getInstance() 호출이 없을때)
    private static final Settings1 INSTANCE = new Settings1();
    public static Settings1 getInstance() {
        return INSTANCE;
    }

    // private 생성자를 생성한다.
    // 오직 해당 파일 안에서만 new 연산자를 사용할 수 있다.
    private Settings1() {}

    /**
     * 이 코드가 안전한가?
     * 안전하지 않다.
     * 멀티쓰레드에 안전하게 사용해야할 필요가 있다.
     * 최초 if 문을 타기전에 여러 쓰레드가 들어오면 모두 new 객체가 생성된다.
     * @return
     */
//    public static Settings1 getInstance() {
//        // 객체가 만들어지지않았을때
//        if (instance == null) {
//            instance = new Settings1();
//        }
//
//        return instance;
//    }
    // 동기 synchronized 처리
    // Lock 을 잡아서 Lock 을 가진 쓰레드만 접근가능하도록 하는 메커니즘이 필요하기 때문에 성능 이슈가 생길 수도 있다.
//    public static synchronized Settings1 getInstance() {
//        // 객체가 만들어지지않았을때
//        if (instance == null) {
//            instance = new Settings1();
//        }
//
//        return instance;
//    }
}
