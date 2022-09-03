package com.itvillage.chapter05.chapter0506;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import com.itvillage.utils.TimeUtil;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

/**
 * 에러 발생시 재시도를 즉시 하지 않고, 지연 시간을 주고 재시도를 하는 예제
 */
public class _6_ObservableRetryExample02 {
    private final static int RETRY_MAX = 5;

    public static void main(String[] args) {
        Observable.just(5)
                .flatMap(
                        num -> Observable
                                .interval(200L, TimeUnit.MILLISECONDS)
                                .map(i -> {
                                    long result;

                                    try {
                                        result = num / i;
                                    } catch(ArithmeticException ex) {
                                        Logger.log(LogType.PRINT, "error: " + ex.getMessage());
                                        throw ex;
                                    }

                                    return result;
                                })
                                // 람다 표현식 사용
                                // retryCount : 재시도 횟수, ex : 예외 객체
                                // subscribe()를 다시 호출해서 생산자쪽이 처음부터 데이터를 다시 통지하게된다.
                                // 발생할때마다 retry가 계속 호출되고, retyrnCount는 증가하게된다.
                                // 12초 지연시킨 후 처리한다.
                                .retry((retryCount, ex) -> {
                                    Logger.log(LogType.PRINT, "# 재시도 횟수: " + retryCount);
                                    TimeUtil.sleep(1000L); // 1초 지연
                                    return retryCount < RETRY_MAX ? true : false;
                                })
                                .onErrorReturn(throwable -> -1L)

                ).subscribe(
                        data -> Logger.log(LogType.ON_NEXT, data),
                        error -> Logger.log(LogType.ON_ERROR, error),
                        () -> Logger.log(LogType.ON_COMPLETE)
                );


        TimeUtil.sleep(6000L);
    }
}
