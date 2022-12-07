package com.example.consumer._workerThread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
Runnable 인터페이스 구현
Runnable 인터페이스 구현한 ConsumerWorker 클래스는 스레드로 실행되며,
생성하고 나면 Runnable 인터페이스로 오버라이드된 run() 메서드가 실행된다.
 */
public class ConsumerWorker implements Runnable {

    private final static Logger logger = LoggerFactory.getLogger(ConsumerWorker.class);
    private String recordValue;

    ConsumerWorker(String recordValue) {
        this.recordValue = recordValue;
    }

    @Override
    public void run() {
        // thread 이름, record 클래스 로그 출력
        logger.info("thread:{}\trecord:{}", Thread.currentThread().getName(), recordValue);
    }
}
