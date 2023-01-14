package org.example.chap02_multiThread._step02_Design_Thread;

/**
 * 쓰레드 생성
 * - 스레드 클래스를 확장하고 실행 메서드를 재정의하기만 하면 된다.
 * - 스레드의 실행이 시작되고, 내부에서 원하는 모든 작업을 수행한다.
 */
class MyThread extends Thread {
    // 2) 이 쓰레드에 할당되어 내부로 들어와서 실행을 시작한다.
    public void run() {
        for (int i = 0; i <= 1000; i++) {
            System.out.print("T");
        }
    }
}

/**
 * Runnable 인터페이스를 구현
 * 그렇기때문에 Thread의 기능들을 획득하지 못했다.
 * (선호되는 방법)
 */
class MyTask2 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i <= 1000; i++) {
            System.out.print("-");
        }
    }
}

public class Main {
    /**
     * JVM은 main 메서드에 스레드를 할당하고, 그 안에서 메인 메서드를 호출한다.
     * @param args
     */
    public static void main(String[] args) {
        MyThread thr = new MyThread();
        // 1) start() 호출시, 실행을 위해 스레드를 제출한 다음, CPU 사이클을 한번 수행한다.
        // 즉시 CPU를 얻는것이 아닌, 순서를 기다리는 것이다.
        // Thread class의 start 메서드
        thr.start();

        MyTask2 task = new MyTask2(); // 스레드 객체 생성
        Thread th2 = new Thread(task); // 할당
        th2.start();

        for (int i = 0; i <= 1000; i++) {
            System.out.print("M");
        }
    }
}
