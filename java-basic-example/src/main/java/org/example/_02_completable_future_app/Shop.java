package org.example._02_completable_future_app;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop {
    private final String name;
    private final Random random;

    public Shop(String name) {
        this.name = name;
        random = new Random((long) name.charAt(0) * name.charAt(1) * name.charAt(2));
    }

    private double calculatePrice(String product) {
        delay(); /* 지연 */
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    /**
     * 동기방식
     */
    public double getPrice(String product) {
        return calculatePrice(product);
    }

    /**
     * 비동기방식
     */
    public Future<Double> getAsyncPrice(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();

        System.out.println("getAsyncPrice() : " + Thread.currentThread().getName());
        /* 새로운 스레드로 수행 */
        new Thread(
                () -> { // Runnable
                    System.out.println("getAsyncPrice() > new Thread(): " + Thread.currentThread().getName());

                    double price = calculatePrice(product);
                    futurePrice.complete(price); // future 에 할당 후 리턴
                }
        ).start();

        return futurePrice;
    }

    public String getName() {
        return name;
    }

    public static void delay() {
        int delay = 1000;

        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
