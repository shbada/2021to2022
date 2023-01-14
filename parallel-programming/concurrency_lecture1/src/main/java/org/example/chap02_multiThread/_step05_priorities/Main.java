package org.example.chap02_multiThread._step05_priorities;

/**
 MIN_PRIORITY - 1 being the minimum priority
 NORM_PRIORITY - 5 is the normally priority, this is the default priority value.
 MAX_PRIORITY - 10 being the max priority.
 */
class CopyTask implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.print("C");
        }
    }
}

class ProgressTask implements Runnable {
    @Override
    public void run() {
        while(true) {
            System.out.print("-");
        }
    }
}

public class Main {

    public static void main(String[] args) {
        CopyTask copyTask = new CopyTask();
        Thread copyThread = new Thread(copyTask);
        copyThread.setPriority(Thread.NORM_PRIORITY + 3); // 수행 기회를 더 많이준다.
        copyThread.start();

        ProgressTask progressTask = new ProgressTask();
        Thread progressThread = new Thread(progressTask);
        progressThread.start();
    }
}