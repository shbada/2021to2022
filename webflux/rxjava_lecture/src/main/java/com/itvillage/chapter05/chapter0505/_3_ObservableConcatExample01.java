package com.itvillage.chapter05.chapter0505;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import com.itvillage.utils.TimeUtil;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

/**
 * concat 을 이용하여 2개 이상의 Observable을 하나의 Observable로 이어 붙여서 통지하는 예제
 *
 * 다수의 Observable 에서 통지된 데이터를 받아서 다시 하나의 Observableㅇ로 통지한다.
 * 하나의 Observable 에서 통지가 끝나면 다음 Observable에서 연이어 통지가 된다.
 * 각 Observable 의 통지 시점과는 상관없이 concat() 함수의 파라미터로 먼저 입력된 Observable의 데이터부터
 * 모두 통지된 후, 다음 Observable 의 데이터가 통지된다.
 */
public class _3_ObservableConcatExample01 {
    public static void main(String[] args) {
        // 0.5초 마다
        Observable<Long> observable1 =
                Observable.interval(500L, TimeUnit.MILLISECONDS)
                        .take(4); // 0 ~ 3까지 총 4개

        // 0.3초 마다 ( 더 빠르다 )
        Observable<Long> observable2 =
                Observable.interval(300L, TimeUnit.MILLISECONDS)
                        .take(5)
                        .map(num -> num + 1000);

        // 데이터 통지 속도와 상관없이 concat 파라미터 순서로 처리한다.
        Observable.concat(observable1, observable2)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));


        TimeUtil.sleep(3500L);

    }
}
