package com.java.effective.item62;

public class Main {
    public static void main(String[] args) {
        // String compoundKey = className + "#" + i.next();
    }
}

class ThreadLocal {
    private ThreadLocal() { } // 객체 생성 불가

    public static class Key { // (권한)
        Key() { }
    }

    // 위조 불가능한 고유 키를 생성한다.
    public static Key getKey() {
        return new Key();
    }

//    public static void set(Key key, Object value);
//    public static Object get(Key key);
}