package org.example._02_completable_future_app;

import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
//        sync();
        async();
    }

    private static void async() {
        Shop shop = new Shop("Hello");
        long start = System.nanoTime();

        /* shop 의 getAsyncPrice 를 호출한 순간 바로 future 가 반환하고, 다른 작업을 계속 수행 */
        Future<Double> futurePrice = shop.getAsyncPrice("water");

        /* -> 다른 작업 수행 */
        doSomethingElse();

        try {
            /* 할일이 없어졌을때 get() 호출하여 결과값 출력 (blocking) */
            System.out.println("Price is " + futurePrice.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void sync() {
        Shop shop = new Shop("Hello");
        long start = System.nanoTime();
        double price = shop.getPrice("water");

        doSomethingElse();

        try {
            System.out.println("Price is " + price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void doSomethingElse() {
        // do something else
    }
}
