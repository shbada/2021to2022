package com.itvillage.chapter01.chapter0101;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class ToDoSample {
    public static void main(String[] args) throws InterruptedException {
        // 리액티브 : 데이터를 발행 -> 데이터 가공 -> 데이터 구독하여 처리한다.
        Observable.just(100, 200, 300, 400, 500)
                // doOnNext : 스레드는 메인스레드에서 실행되는데, 데이터가 100 ~ 500 각각이 발행될때 실행된다.
                .doOnNext(data -> System.out.println(getThreadName() + " : " + "#doOnNext() : " + data))
                // subscribeOn : 코드가 메인메서드에 있는데, 이를 작성하면 메인 스레드가 아닌 다른 스레드에서 실행된다.
                // 다른 스레드 : RxCachedThreadScheduler
                // 해당 스레드가 실행되기 전에 main 메서드가 종료되어 아무것도 수행되지 않는다.-> sleep(500)으로 0.5초 메인스레드를 지연시키자.
                .subscribeOn(Schedulers.io())
                // observeOn : RxCachedThreadScheduler, RxComputationThreadPool
                // 데이터 발행시에는 RxCachedThreadScheduler 스레드 이름을 가진다.
                // 발행된 데이터를 가공하고 구독하여 처리하는 스레드가 RxComputationThreadPool 이름으로 가진다.
                .observeOn(Schedulers.computation())
                // 데이터 필터링
                .filter(number -> number > 300)
                // 필터링된 데이터를 전달받아서 출력
                .subscribe(num -> System.out.println(getThreadName() + " : result : " + num));

        Thread.sleep(500); // 메인 스레드의 동작을 0.5초 딜레이시킨다.
    }

    public static String getThreadName(){
        return Thread.currentThread().getName();
    }
}
