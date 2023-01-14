package org.example.step3_start와_run;

public class _2_ThreadEx2 {
    public static void main(String[] args) throws Exception {
        ThreadEx2_1 t1 = new ThreadEx2_1();
        /*
        throwException
        run // main 쓰레드는 이미 종료되어서 호출스택에 존재하지 않는다.
         */
        t1.start();
    }
}

class ThreadEx2_1 extends Thread {
    public void run() {
        // 한 쓰레드가 예외가 발생해서 종료되어도 다른 쓰레드의 실행에는 영향을 미치지 않는다.
        throwException();
    }

    /**
     * 고의로 에러 발생
     */
    public void throwException() {
        try {
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}