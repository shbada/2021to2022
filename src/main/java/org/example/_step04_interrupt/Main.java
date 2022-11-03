package org.example._step04_interrupt;

/**
 * 스레드 중간에 멈춰보자.
 */
class MyThread extends Thread {

    public void run() {

        for( ;; ) {
            // 인터럽트 신호를 읽기
            if (interrupted()) {
                System.out.println("Thread is interrupted hence stopping..");
                break;
            }

            System.out.print("T");
        }
    }
}

public class Main {

    public static void main(String[] args) {
        MyThread thr = new MyThread();
        thr.start();

        try {
            // sleep : 주어진 시간 간격 동안 차단
            // 1초 동안 스레드를 차단
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // sleep 수행 중에 외부에서 인터럽트가 도착해서 인터럽트 상태가 설정된 경우 에러 발생
            e.printStackTrace();
        }

        // stop() 은 사용하지 말아라. 스레드를 갑자기 종료하는건 좋지않다.
//        thr.stop();
        // 인터럽트를 받은 쓰레드에는 '인터럽트 상태(Interrupt State)'가 설정된다.
        thr.interrupt();
    }
}