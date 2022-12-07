package com.itvillage.chapter05.chapter0506;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import com.itvillage.utils.TimeUtil;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

/**
 * onErrorReturn()를 사용해 예외 발생 시, 우리가 원하는 값을 전달하는 예제
 * - 예외가 발생될 가능성이 있는 부분에 대해서 사전에 처리를 선언할 수 있다.
 * - 소비자가 예상되는 예외를 모두 사전에 알고 처리하긴 힘들기때문에 생산자쪽에서 예외 처리를 사전에 해두고 소비자는 선언된
 * 예외 상황을 보고 그에 맞는 적절한 처리를 할 수 있다.
 */
public class _3_ObservableOnErrorReturnExample {
    public static void main(String[] args) {
        Observable.just(5)
                .flatMap(num -> Observable
                        // 0.2초에 1번씩
                        .interval(200L, TimeUnit.MILLISECONDS)
                        // 5개의 숫자 통지
                        .take(5)
                        // 5를 0, 1, 2, 3, 4 순서로 나누는것
                        .map(i -> num / i)
                        // 숫자를 0으로 나누면 에러가 발생하고, 이 메서드를 수행한다.
                        .onErrorReturn(exception -> { // 예외를 전달받는다.
                            // ArithmeticException이 발생했다면
                            if (exception instanceof ArithmeticException) {
                                Logger.log(LogType.PRINT, "계산 처리 에러 발생: " + exception.getMessage());
                            }

                            // 에러 발생시 리턴할 값을 지정한다.
                            return -1L;
                        })
                )
                // 소비자 쪽보다 생산자쪽의 에러 메서드가 먼저 수행된다.
                // 생산자쪽의 에러 메서드가 수행후, 소비자는 error 가 아닌 data 로 받아서 수행한다.
                .subscribe(
                        data -> {
                            if(data < 0)
                                Logger.log(LogType.PRINT, "# 예외를 알리는 데이터: " + data);
                            else
                                Logger.log(LogType.ON_NEXT, data);
                        },
                        error -> Logger.log(LogType.ON_ERROR, error),
                        () -> Logger.log(LogType.ON_COMPLETE)
                );

        TimeUtil.sleep(1000L);
    }
}
