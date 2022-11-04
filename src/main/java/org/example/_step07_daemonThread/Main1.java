package org.example._step07_daemonThread;

public class Main1 {

    public static void main(String[] args) {

        System.out.println("System threads..........");

        Thread thr = Thread.currentThread();
        ThreadGroup grp = thr.getThreadGroup();
        while (grp.getParent() != null) {
            grp = grp.getParent();
        }

        Thread [] threads = new Thread[10];
        int n = grp.enumerate(threads); // 해당 그룹의 스레드를 나열

        // 데몬쓰레드 여부 : isDaemon()
        for (int i=0; i < n; i++) {
            System.out.println(
                    "Thread Name: " + threads[i].getName() +
                            " ; isDaemon: " + threads[i].isDaemon());
        }
    }
}
