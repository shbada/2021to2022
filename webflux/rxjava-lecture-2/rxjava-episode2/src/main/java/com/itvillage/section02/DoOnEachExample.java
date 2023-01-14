package com.itvillage.section02;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import io.reactivex.Observable;

/**
 * doOnEach 를 이용해 doOnNext, doOnComplete, doOnError를 한꺼번에 처리하는 예제
 * - doOnNext, doOnComplete, doOnOerror을 한번에 처리할 수 있다.
 * - Notification 객체를 함수형 인터페이스의 파라미터로 전달받아서 처리한다.
 */
public class DoOnEachExample {
    public static void main(String[] args) {
        Observable.range(1, 5)
                // notification 를 파라미터로 받는다.
                .doOnEach(notification -> {
                    // onNext
                    if(notification.isOnNext())
                        Logger.log(LogType.DO_ON_NEXT, "# 생산자: 데이터 통지 - " + notification.getValue());
                    // onError
                    else if(notification.isOnError())
                        Logger.log(LogType.DO_ON_ERROR, "# 생산자: 에러 발생 - " + notification.getError());
                    // 그 외
                    else
                        Logger.log(LogType.DO_ON_COMPLETE, "# 생산자: 데이터 통지 완료");
                })
                .subscribe(
                        data -> Logger.log(LogType.ON_NEXT, data),
                        error -> Logger.log(LogType.ON_ERROR, error),
                        () -> Logger.log(LogType.ON_COMPLETE)
                );
    }
}
