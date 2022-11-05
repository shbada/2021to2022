package org.example._step11_static_data_sync;

/**
 * 멀티쓰레드 환경에서 정적 컨텐츠를 수정할때마다 스레드 안전하게 하기 위해서는, 클래스 객체를 잠가야한다.
 */
public class Main {
}

class MainSample {

    // 클래스 개체를 잠겨야한다. (Solution1)
    // 정적 변수를 수정할때마다 클래스 객체를 잠궈야 스레드 안전하다.
    static int a = 5; // 스레드 안전하지 않다.
    int b = 10; //  각 인스턴스별로 별도로 관리되기 때문에 쓰레드 안전하다.

    public synchronized void increment( ) {
        a++;
        b++;
    }
}