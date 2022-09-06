package com.itvillage.section02;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import io.reactivex.Observable;

/**
 * onComplete 이벤트 발생 전에 호출되는 doOnComplete 의 사용 예제
 *
 * - 생산자가 완료를 통지하는 시점에, 지정된 작업을 처리할 수 있다.
 * - onComplete 이벤트가 발생하기 직전에 실행된다.
 */
public class DoOnCompleteExample {
    public static void main(String[] args) {
        Observable.range(1, 5)
                .doOnComplete(() -> Logger.log(LogType.DO_ON_COMPLETE, "# 생산자: 데이터 통지 완료"))
                .subscribe(
                        data -> Logger.log(LogType.ON_NEXT, data),
                        error -> Logger.log(LogType.ON_ERROR, error),
                        () -> Logger.log(LogType.ON_COMPLETE)
                );
    }
}
