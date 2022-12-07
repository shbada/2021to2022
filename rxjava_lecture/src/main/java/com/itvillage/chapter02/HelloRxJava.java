package com.itvillage.chapter02;

import io.reactivex.Observable;

/**
 * Reactive Streams란?
 * 리액티브 프로그래밍에 대한 인터페이스만 제공한다.
 * -> Publisher, Subscriber, Subscription, Processor
 * RxJAVA
 * Reactive Streams의 인터페이스들을 구현한 구현체다.
 *
 * 1) Publisher : 데이터를 생산하고 통지한다.
 * 2) Subscriber : 통지된 데이터를 전달받아서 처리한다.
 * 3) Subscription : 전달 받을 데이터의 개수를 요청하고 구독을 해지한다.
 * 4) Processor : Publisher,Subscriber에 모두 있음
 */
public class HelloRxJava {
    public static void main(String[] args){
        // 데이터 생성 및 통지
        Observable<String> observable = Observable.just("Hello", "RxJava!");

        // 소비자쪽 코드 (구독하여 데이터 출력)
        // 구독된 데이터를 전달받아서 출력하는것
        observable.subscribe(data -> System.out.println(data));

        Observable.just("Hello", "RxJava!")
                .subscribe(data -> System.out.println(data));

    }
}
