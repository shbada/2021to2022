package com.itvillage.chapter01.chapter0102;

import io.reactivex.Observable;

public class MarbleExample02 {
    public static void main(String[] args){
        // Observable 을 생성하는 just 메서드
        Observable<Integer> observable = Observable.just(2, 25, 30, 15, 6);

        // observable 을 구독하게되면, 데이터 그대로 출력
        observable.subscribe(num -> System.out.println(num));

    }
}
