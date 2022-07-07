package com.project.springbatch._43_multiThread;

import org.springframework.batch.core.ItemWriteListener;

import java.util.List;

public class MultiThreadCustomWriteListener implements ItemWriteListener<MultiThreadCustomer> {

    @Override
    public void beforeWrite(List<? extends MultiThreadCustomer> items) {

    }

    @Override
    public void afterWrite(List<? extends MultiThreadCustomer> items) {
        System.out.println("Thread : " + Thread.currentThread().getName() + ", write items : " + items.size());

    }

    @Override
    public void onWriteError(Exception exception, List<? extends MultiThreadCustomer> items) {

    }
}
