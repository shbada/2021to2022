package com.itvillage.chapter01.chapter0102;

import io.reactivex.Observable;

public class MarbleExample01 {
    public static void main(String[] args){
        Observable.just(1, 25, 9, 15, 7, 30) // 데이터 발행
                .filter(x -> x > 10) // 필터링
                .subscribe(x -> System.out.println(x)); // 구독자가 전달받아, 출력
    }
}
