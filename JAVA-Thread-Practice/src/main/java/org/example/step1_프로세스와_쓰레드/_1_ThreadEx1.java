package org.example.step1_프로세스와_쓰레드;

/**
 * 쓰레드를 구현한다는 것은, 그저 쓰레드를 통해 작업하고자 하는 내용으로 run()의 몸통 {}을 채우는 것일 뿐이다.
 */
class _1_ThreadEx1 {
    public static void main(String[] args) {
        ThreadEx1_1 t1 = new ThreadEx1_1();

        /* Runnable 인터페이스를 구현한 경우, Runnable 인터페이스를 구현한 클래스의 인스턴스를 생성한 다음,
        이 인스턴스를 Thread 클래스의 생성자의 매개변수로 제공해야한다.
         */
        Runnable r = new ThreadEx1_2();
        Thread t2 = new Thread(r); // 생성자 Thread (Runnable target)

        // start()를 호출해야만 쓰레드가 실행된다.
        // 실행대기 상태에 있다가 자신의 차례가 되면 실행된다. 실행대기중인 쓰레드가 하나도 없으면 곧바로 실행된다.
        // 한번 실행이 종료된 쓰레드는 다시 실행할 수 없다. 즉, 하나의 스레드에 대해 start()가 한번만 호출될 수 있다.
        t1.start();
        t2.start();
    }
}

/**
 * Thread 클래스 상속
 */
class ThreadEx1_1 extends Thread {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(getName()); // 조상인 Thread의 getName()을 호출
        }
    }
}

/**
 * Runnable 인터페이스 상속
 */
class ThreadEx1_2 implements Runnable {
    public void run() {
        for (int i=0; i<5; i++) {
//			Thread.currentThread();	// 현재 실행 중인 Thread를 반환
            // 쓰레드 이름을 지정하지 않으면 'Thread-번호'의 형식으로 이름이 정해진다.
            System.out.println(Thread.currentThread().getName());
        }
    }
}