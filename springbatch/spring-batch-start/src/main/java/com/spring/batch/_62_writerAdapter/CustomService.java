package com.spring.batch._62_writerAdapter;

public class CustomService<T> {

    public void customWrite(T item){
        System.out.println(item);
    }
}

