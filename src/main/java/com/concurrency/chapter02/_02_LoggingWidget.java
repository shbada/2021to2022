package com.concurrency.chapter02;

/**
 * 암묵적인 락 (=자바에 내장된 락)
 * 재진입이 가능하여 특성 스레드가 자기가 이미 획득한 락을 다시 확보할 수 있다.
 * 재진입성은 확보 요청 단위가 아닌 스레드 단위로 락을 얻는다는 것을 의미한다.
 *
 *
 * 락의 재진입이 가능하지 않았다면 데드락에 빠졌을 코드다.
 */
public class _02_LoggingWidget extends _01_Widget {
    /**
     * synchronized 선언
     * Widget, LoggingWidget 둘다 각각 진행전에 락을 얻으려고 시도한다.
     *
     * 암묵적인 락이 재진입 가능하지 않았다면, 이미 락을 누군가가 확보했기 때문에
     * super.doSomething()호출에서 락을 얻을 수 없게되고, 결과적으로 확보할 수 없는 락을 기다리면서 영원히 멈춰있었을 것이다.
     */
    public synchronized void doSomething() {
        System.out.println(toString() + ": calling doSomething");
        // 해당 메서드도 synchronized 로 선언되어있다.
        super.doSomething();
    }
}
