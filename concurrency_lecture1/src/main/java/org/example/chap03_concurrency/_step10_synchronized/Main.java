package org.example.chap03_concurrency._step10_synchronized;

class Sample {

    private int x;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    /*
     * Try removing synchronized.
     * 메서드 전체를 synchronized 보다는, 사용되는 block을 synchronized 하는게 더 좋다.
     */
    public synchronized void incr() {

        // read the value of x.
        int y = getX();

        // Increment the value
        y++;

        try {
            Thread.sleep(1);
        } catch(Exception e) {

        }

        // set x to new value.
        setX(y);
    }

    // 위와 동일한 결과
    public void incr2() {
        synchronized(this) {
            // read the value of x.
            int y = getX();

            // Increment the value
            y++;

            try {
                Thread.sleep(1);
            } catch(Exception e) {

            }

            // set x to new value.
            setX(y);
        }
    }
}

class MyThread extends Thread {

    Sample obj;

    public MyThread(Sample obj) {
        this.obj = obj;
    }

    public void run() {
        obj.incr();

//        synchronized (obj) {
//            obj.incr();
//        }
    }

}


public class Main {

    public static void main(String[] args) {

        Sample obj = new Sample();
        obj.setX(10);

        // threads t1 and t2
        MyThread t1 = new MyThread(obj);
        MyThread t2 = new MyThread(obj);

        t1.start();
        t2.start();

        // Here main thread called the join operationon t1 and t2.
        // join() operations waits for thread to complete before returning.
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println( obj.getX() );
    }

}