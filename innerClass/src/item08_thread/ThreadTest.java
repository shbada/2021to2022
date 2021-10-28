package item08_thread;

class MyThread extends Thread {
    public void run() {
        int i;
        for (i = 1; i <= 200; i++) {
            System.out.println(i + "\t");
        }
    }
}

public class ThreadTest {
    /**
     * main 실행시 main 쓰레드 1개 실행
     * @param args
     */
    public static void main(String[] args) {
        /*
            Thread[main,5,main]start
            Thread[main,5,main]end
         */
        /** thread 3개 - main 까지 */
        System.out.println(Thread.currentThread() + "start");

        MyThread thread = new MyThread();
        thread.start();

        MyThread thread2 = new MyThread();
        thread2.start();

        System.out.println(Thread.currentThread() + "end");

        /** main 이 제일 먼저 종료 */
    }
}
