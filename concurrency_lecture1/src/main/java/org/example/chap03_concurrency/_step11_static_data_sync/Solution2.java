package org.example.chap03_concurrency._step11_static_data_sync;

public class Solution2 {
}

class Solution2Sample {

    static int a = 5;
    int b = 10;

    // This method is static and hence it locks the Class object.
    public static synchronized void incrementA( ) {
        a++;
    }

    // This method is non static and hence it locks the object on which it is invoked.
    public synchronized void increment( ) {
        incrementA();
        b++;
    }

    // ....

}