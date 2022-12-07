package item08_thread;

class MyThreadRunnable implements Runnable {
    @Override
    public void run() {
        int i;
        for (i = 1; i <= 200; i++) {
            System.out.println(i + "\t");
        }
    }
}

public class ThreadRunnableTest {
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

        MyThreadRunnable runnable = new MyThreadRunnable();
        Thread th1 = new Thread(runnable);
        th1.start();

        Thread th2 = new Thread(runnable);
        th2.start();

        System.out.println(Thread.currentThread() + "end");

        /** main 이 제일 먼저 종료 */

        // 익명클래스
        Runnable run = new Runnable() {
            @Override
            public void run() {
                System.out.println("익명객체");
            }
        };

        run.run();

        // lamda
        // 익명클래스
        Runnable run2 = () -> System.out.println("익명객체");

        run.run();
    }
}
