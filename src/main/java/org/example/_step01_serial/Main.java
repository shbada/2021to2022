package org.example._step01_serial;

public class Main {
    public static void main(String[] args) {
        // 1번째 작업 (직렬)
//        Task t1 = new Task();
//        t1.doTask();

        // 2번째 작업 (switching)
        Task2 t1 = new Task2();
        t1.start();


        // 2번째 작업
        for (int i = 0; i <= 150; i++) {
            System.out.print("T");
        }
    }
}

class Task {
    public void doTask() {
        for (int i = 0; i <= 150; i++) {
            System.out.print("M");
        }
    }
}

class Task2 extends Thread {
    public void run() {
        doTask();
    }

    public void doTask() {
        for (int i = 0; i <= 150; i++) {
            System.out.print("M");
        }
    }
}
