package com.itvillage.chapter03.chapter0301;

import io.reactivex.Flowable;

/**
 * 차가운 생산자
 * - 생상자는 소비자가 구독할때마다 데이터를 처음부터 새로 통지한다.
 * - 데이터를 통지하는 새로운 타임라인이 생성된다.
 * - 소비자는 구독 시점과 상관없이 통지된 데이터를 처음부터 전달받을 수 있다.
 */
public class ColdPublisherExample {
    public static void main(String[] args){
        // Observable, Flowable : Cold 생산자
        Flowable<Integer> flowable = Flowable.just(1, 3, 5, 7);

        // Publisher가 통지한 데이터를 구독하게되면, 구독자는 구독 시점에 상관없이 처음부터 데이터를 받을 수 있다.
        flowable.subscribe(data -> System.out.println("구독자1: " + data));
        flowable.subscribe(data -> System.out.println("구독자2: " + data));
    }
}
