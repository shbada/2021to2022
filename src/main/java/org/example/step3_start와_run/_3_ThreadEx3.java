package org.example.step3_start와_run;

public class _3_ThreadEx3 {
    public static void main(String args[]) throws Exception {
        ThreadEx3_1 t1 = new ThreadEx3_1();
        // 쓰레드가 새로 생성되지 않았다. 그저 ThreadEx3_1의 run()이 호출되었을 뿐이다.
        /*
        throwException
        run
        main
         */
        t1.run();
    }
}

class ThreadEx3_1 extends Thread {
    public void run() {
        throwException();
    }

    public void throwException() {
        try {
            throw new Exception();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
