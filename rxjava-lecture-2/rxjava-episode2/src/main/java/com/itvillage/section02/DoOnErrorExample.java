package com.itvillage.section02;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import io.reactivex.Observable;

/**
 * onError 이벤트 발생 전에 호출되는 doOnError 의 사용 예제
 *
 * - 생산자가 에러를 통지하는 시점에, 지정된 작업을 처리할 수 있다.
 * - onError 이벤트가 발생하기 직전에 실행된다.
 * - 통지된 에러 객체가 함수형 인터페이스의 파라미터로 전달되므로 에러 상태를 확인할 수 있다.
 */
public class DoOnErrorExample {
    public static void main(String[] args) {
        Observable.just(3, 6, 9, 12, 15, 20)
                .zipWith(Observable.just(1, 2, 3, 4, 0, 5), (a, b) -> a / b)
                // 생산자쪽에서 데이터가 에러 없이 통지되었는지 확인하는 용도로 사용할 수 있다.
                .doOnError(error -> Logger.log(LogType.DO_ON_ERROR, "# 생산자: 에러 발생 - " + error.getMessage()))
                .subscribe(
                        data -> Logger.log(LogType.ON_NEXT, data),

                        // 최종적으로 소비자 쪽에서 에러 객체를 전달받아서 출력한다.
                        error -> Logger.log(LogType.ON_ERROR, error)
                );
    }
}
