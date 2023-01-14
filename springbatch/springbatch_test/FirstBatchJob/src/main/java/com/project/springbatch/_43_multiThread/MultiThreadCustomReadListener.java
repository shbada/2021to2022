package com.project.springbatch._43_multiThread;

import org.springframework.batch.core.ItemReadListener;

public class MultiThreadCustomReadListener implements ItemReadListener<MultiThreadCustomer> {

    @Override
    public void beforeRead() {

    }

    @Override
    public void afterRead(MultiThreadCustomer item) {
        System.out.println("Thread : " + Thread.currentThread().getName() + ", read item : " + item.getId());
    }

    @Override
    public void onReadError(Exception ex) {

    }
}