package com.itvillage.chapter05.chapter0503;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import com.itvillage.utils.TimeUtil;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

/**
 * 원본 소스의 처리 속도가 빨라서 현재 처리 중이던 작업을 중단하는 예제
 *
 * 받은 데이터를 반환하여 새로운 Observable로 반환한다.
 * switchMap은 순서를 보장하지만 새로운 데이터가 통지되면 현재 처리중이던 작업을 바로 중단한다.
 */
public class _8_ObservableSwitchMapExample01 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("# start : " +TimeUtil.getCurrentTimeFormatted());
        // 0.1초가 지나면 2 통지
        // 또 지나면 3 통지
        Observable.interval(100L, TimeUnit.MILLISECONDS)
                .take(4)
                .skip(2)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                // 3단만 출력되고 2단은 출력되지 않았다.
                // 2가 통지됬을때 interval 함수는 아직 0.3초가 지나지않아서 통지되지 않고있다.
                // 근데 바깥쪽에서 0.2초가 되서 3초가 다시 전달되어 2가 중지되고 3만 수행됨
                .switchMap( // concatMap -> switchMap
                        num -> Observable.interval(300L, TimeUnit.MILLISECONDS)
                                        .take(10)
                                        .skip(1)
                                        .map(row -> num + " * " + row + " = " + num * row)
                )
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        Thread.sleep(5000);
    }
}
