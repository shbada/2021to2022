package org.example.atomic;

public class Counter {
    private int c = 0;

    public void increment() {
        c++;

        // 현재 쓰레드 이름을 구함.
        String threadName = Thread.currentThread().getName();

        /* 스레드 이름과 데이터 값을 출력한다. */
        System.out.printf(" -> Thread Name : %s, Stream Value : %s\n", threadName, c);
    }

    public void decrement() {
        c--;
    }

    public int value() {
        return c;
    }
}
