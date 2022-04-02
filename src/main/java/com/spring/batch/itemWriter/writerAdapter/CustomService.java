package com.spring.batch.itemWriter.writerAdapter;

public class CustomService<T> {

    public void customWrite(T item){
        System.out.println(item);
    }
}

