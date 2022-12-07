package com.itvillage.chapter05.chapter0506;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import com.itvillage.utils.TimeUtil;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

/**
 * 데이터 통지 중 에러가 발생했을때 데이터 통지를 재시도한다.
 * 즉, onError 이벤트가 발생하면 subscribe()를 다시 호출하여 재구독한다.
 */
public class _5_ObservableRetryExample01 {
    public static void main(String[] args) {
        Observable.just(5) // 5 통지
                .flatMap(
                        num -> Observable
                                // 0.2초마다
                                .interval(200L, TimeUnit.MILLISECONDS)
                                .map(i -> {
                                    long result;

                                    try {
                                        result = num / i; // 0으로 나누게되므로 ArithmeticException 에러 발생
                                    } catch(ArithmeticException ex) {
                                        Logger.log(LogType.PRINT, "error: " + ex.getMessage());
                                        throw ex;
                                    }

                                    return result;
                                })
                                // subscribe() 함수를 다시 호출해서 생산자쪽에서 데이터를 처음부터 다시 통지하게된다.
                                // 5번 시도해도 계속 오류가 발생한다면 최종적으로 onErrorReturn 메서드를 수행한다.
                                .retry(5)
                                .onErrorReturn(throwable -> -1L)
                ).subscribe(
                        data -> Logger.log(LogType.ON_NEXT, data),
                        error -> Logger.log(LogType.ON_ERROR, error),
                        () -> Logger.log(LogType.ON_COMPLETE)
                );

        TimeUtil.sleep(5000L);
    }
}
