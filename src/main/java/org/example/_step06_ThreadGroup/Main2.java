package org.example._step06_ThreadGroup;

class MyTask implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public class Main2 {

    public static void main(String[] args) {

        // 쓰레드그룹 생성
        ThreadGroup myGroup = new ThreadGroup("MyGroup");
        myGroup.setMaxPriority(4); // 최대 우선 순위 설정 (이 그룹에 들어오는 모든 스레드는 최대 우선순위가 4로 설정된다.)

        // ASSOCIATING A THREAD WITH THREAD GROUP
        Thread myThread = new Thread(myGroup, new MyTask(), "DemoThread"); // 쓰레드이름 지정
        myThread.start();

        System.out.println("System threads..........");
        Thread thr = Thread.currentThread();
        ThreadGroup grp = thr.getThreadGroup();
        while (grp.getParent() != null) {
            grp = grp.getParent();
        }
        grp.list();
    }
}