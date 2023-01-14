package org.example.chap02_multiThread._step06_ThreadGroup;

public class Main1 {
    public static void main(String[] args) {
        System.out.println("System threads..........");
        Thread thr = Thread.currentThread();
        ThreadGroup grp = thr.getThreadGroup();
        while (grp.getParent() != null) {
            grp = grp.getParent();
        }
        grp.list();
    }
}
