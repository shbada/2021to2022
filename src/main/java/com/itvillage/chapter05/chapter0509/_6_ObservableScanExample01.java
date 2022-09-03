package com.itvillage.chapter05.chapter0509;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import io.reactivex.Observable;

/**
 * scan을 이용해 1부터 10까지의 sum을 구하는 예제(초기값 없음)
 * - 집계 중간 결과를 계속해서 출력한다.
 *
 * 데이터가 통지될 때마다 소비자 쪽에서 확인할 수 있다.
 */
public class _6_ObservableScanExample01 {
    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                // 합성이 될때마다 중간 결과를 출력한다.
                .scan((x, y) -> x + y)
                .subscribe(result -> Logger.log(LogType.ON_NEXT, "# 1부터 10까지의 누적 합계: " + result));
    }
}
