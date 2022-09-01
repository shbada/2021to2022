package com.itvillage.chapter05.chapter0503;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import io.reactivex.Observable;

/**
 * FlapMap을 이용한 1 대 다 mapping 예제
 * flatMap 함수는 변환된 여러개의 데이터를 담고있는 새로운 observable를 반환한다.
 * 데이터 한개로 여러 데이터를 통지할 수 있다.
 */
public class _3_ObservableFlatMapExample01 {
    public static void main(String[] args) {
        Observable.just("Hello")
                // 1 -> N (3)
                .flatMap(hello -> Observable.just("자바", "파이썬", "안드로이드").map(lang -> hello + ", " + lang))
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
