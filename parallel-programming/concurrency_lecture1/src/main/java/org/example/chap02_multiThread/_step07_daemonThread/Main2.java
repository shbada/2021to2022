package org.example.chap02_multiThread._step07_daemonThread;

class MyTask implements Runnable {

    @Override
    public void run() {
        for (;;) {
            System.out.print("T");
        }
    }
}
public class Main2 {
    public static void main(String[] args) {
        Thread thr = new Thread(new MyTask());
        // 데몬스레드로 지정하면 main 스레드 종료시 해당 쓰레드도 종료된다.
        // 데몬 스레드의 주요 목적은 백그라운드 작업을 수행하는 것

        thr.setDaemon(true);
        thr.start();

        // 메인 스레드는 200M을 인쇄한 후에 종료된다.
        for (int i=1; i <= 200; i++) {
            System.out.print(" M ");
        }
    }
}
