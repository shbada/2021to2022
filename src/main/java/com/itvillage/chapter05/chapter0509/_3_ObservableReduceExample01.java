package com.itvillage.chapter05.chapter0509;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import io.reactivex.Observable;

/**
 * reduce를 이용해 1부터 10까지의 sum을 구하는 예제(초기값 없음)
 *
 * Observable이 통지한 데이터를 이용해서 어떤 결과를 일정한 방식으로 합성한 후, 최종 결과를 반환한다.
 * Observable이 통지한 데이터가 숫자일 경우 파라미터로 지정한 함수형 인터페이스에 정의된 계산 방식으로 값을 집계할 수 있다.
 */
public class _3_ObservableReduceExample01 {
    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                // 누적을 의미
                .reduce((x, y) -> x + y)
                .subscribe(result -> Logger.log(LogType.ON_NEXT, "# 1부터 10까지의 누적 합계: " + result));
    }
}
