package com.itvillage.chapter05.chapter0503;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import com.itvillage.utils.TimeUtil;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

/**
 * 순서를 보장해주는 concatMap 예제
 * 순차적으로 실행되기때문에 flatMap보다 느리다.
 *
 * 받은 데이터를 변환하여 새로운 Observable을 반환한다.
 * 데이터의 처리 순서는 보장하지만 처리중인 Observable의 처리가 끝나야 다음 Observable이 실행된다.
 */
public class _6_ObservableConcatMapExample01 {
    public static void main(String[] args) {
        TimeUtil.start();
        Observable.interval(100L, TimeUnit.MILLISECONDS) // 0.1 초에 한번씩 데이터 통지
                .take(4) // 2, 3만 통지
                .skip(2) // 0, 1 통과
                .concatMap( // 2, 3을 받는다
                        num -> Observable.interval(200L, TimeUnit.MILLISECONDS)
                                .take(10) // 1 ~ 9
                                .skip(1) // 0 통과
                                .map(row -> num + " * " + row + " = " + num * row) // 2단, 3단 출력
                ).subscribe(
                        data -> Logger.log(LogType.ON_NEXT, data),
                        error -> {},
                        () -> {
                            TimeUtil.end();
                            TimeUtil.takeTime();
                        }
                );

        TimeUtil.sleep(5000L);
    }
}
