package com.itvillage.chapter05.chapter0506;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import com.itvillage.utils.TimeUtil;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

/**
 * 데이터를 처리하다가 예외가 발생할 경우 일반적인 예제
 * 0으로 나누는 부분에서 예외가 발생한다.
 *
 * RxJava에서 에러를 처리하는 일반적인 방식의 예제
 *  - RxJava에서는 에러 발생 시, Observable을 생성한 함수에서 onError()를 호출하고,
 *  - subscribe의 onError()에서 해당 error를 받아서 처리하는 구조를 가진다.
 */
public class _2_GeneralErrorHandleExample {
    public static void main(String[] args) {
        Observable.just(5)
                .flatMap(num -> Observable
                        .interval(200L, TimeUnit.MILLISECONDS)
                        // doOnNext() | RxComputationThreadPool-1 | 11:31:08.425 | 0
                        .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                        .take(5) // 5개의 숫자 통지
                        .map(i -> num / i)) // i 의 첫번째 값은 0 -> 에러 발생 (숫자를 0으로 나눌 수 없다)
                .subscribe( // 두번째 람다 표현식에서 error 처리를 할 수 있다.
                        data -> Logger.log(LogType.ON_NEXT, data),
                        // 에러 처리
                        // onERROR() | RxComputationThreadPool-1 | 11:31:08.426 | java.lang.ArithmeticException: / by zero
                        error -> Logger.log(LogType.ON_ERROR, error),
                        () -> Logger.log(LogType.ON_COMPLETE)
                );

        TimeUtil.sleep(1000L);
    }
}
