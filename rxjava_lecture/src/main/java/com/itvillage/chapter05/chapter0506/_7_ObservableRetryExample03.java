package com.itvillage.chapter05.chapter0506;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import com.itvillage.utils.TimeUtil;
import io.reactivex.Observable;

/**
 * 에러 발생 시, 데이터 통지를 처음부터 다시 하는것을 보여주는 예제
 *
 * retry
 * 데이터 통지 중 에러가 발생했을때, 데이터 통지를 재시도한다.
 * 즉, onError 이벤트가 발생하면 subscribe()를 다시 호출하여 재구독한다.
 * 에러가 발생한 시점에 통지에 실패한 데이터만 다시 통지되는 것이 아니라 처음부터 다시 통지된다.
 */
public class _7_ObservableRetryExample03 {
    private final static int RETRY_MAX = 5;
    public static void main(String[] args) {
        Observable.just(10, 12, 15, 16) // 통지 데이터
                // 데이터 결합
                // 인덱스가 동일한 데이터끼리 결합한다. (10-1, 12-2, 15-0, 16-4)
                .zipWith(Observable.just(1, 2, 0, 4), (a, b) -> {
                    int result;

                    try {
                        // 세번째인 15 / 0 일때 오류가 발생할것
                        result = a / b;
                    } catch(ArithmeticException ex) {
                        Logger.log(LogType.PRINT, "error: " + ex.getMessage());
                        throw ex;
                    }

                    return result;
                })
                // 3번 다시 재시도한다.
                // 에러가 발생한 데이터 자체만 다시 통지가 아닌 원본 데이터를 다시 구독하게되어 처음부터 다시 통지한다.
                .retry(3)
                // 3번 재시도 후에도 계속 에러가 발생하면 수행
                .onErrorReturn(throwable -> -1)
                .subscribe(
                data -> Logger.log(LogType.ON_NEXT, data),
                error -> Logger.log(LogType.ON_ERROR, error),
                () -> Logger.log(LogType.ON_COMPLETE)
        );

        TimeUtil.sleep(5000L);
    }
}
