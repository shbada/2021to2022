package com.spring.batch._57_readerAdapter;

public class CustomService<T> {

    private int cnt = 0;

    public T joinCustomer(){
        return (T)("item" + cnt++);
    }
}
