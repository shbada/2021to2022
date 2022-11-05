package org.example._step11_static_data_sync;

public class Solution1 {
}

class Solution1Sample {

    static int a = 5;
    int b = 10;

    public  void increment( ) {
        // lock the Class object before modifying
        // static content.
        synchronized(Solution1Sample.class) {
            a++;
        }

        // lock the object before modifying
        // instance members.
        synchronized(this) {
            b++;
        }
    }

    // ....
}