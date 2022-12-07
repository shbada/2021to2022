package org.example.atomic;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
    private AtomicInteger c = new AtomicInteger(0);

    public void increment() {
        // 현재 쓰레드 이름을 구함.
        String threadName = Thread.currentThread().getName();

        /* 스레드 이름과 데이터 값을 출력한다. */
        System.out.printf(" -> Thread Name : %s, Stream Value : %s\n", threadName, c);

        c.incrementAndGet();
    }

    public void decrement() {
        c.decrementAndGet();
    }

    public int value() {
        return c.get();
    }
}
