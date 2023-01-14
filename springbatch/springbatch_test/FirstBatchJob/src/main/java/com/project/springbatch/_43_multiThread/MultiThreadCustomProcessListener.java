package com.project.springbatch._43_multiThread;

import org.springframework.batch.core.ItemProcessListener;

public class MultiThreadCustomProcessListener implements ItemProcessListener<MultiThreadCustomer, MultiThreadCustomer> {
    @Override
    public void beforeProcess(MultiThreadCustomer item) {

    }

    @Override
    public void afterProcess(MultiThreadCustomer item, MultiThreadCustomer result) {
        System.out.println("Thread : " + Thread.currentThread().getName() + ", process item : " + item.getId());
    }

    @Override
    public void onProcessError(MultiThreadCustomer item, Exception e) {

    }
}
