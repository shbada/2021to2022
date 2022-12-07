package com.itvillage.chapter05.chapter0507;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import com.itvillage.utils.TimeUtil;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

/**
 * 소비자가 구독시, 데이터 생성 및 통지 자체를 지연시키는 예제.
 * 즉, delay()는 소비자가 구독 시, 생성 및 통지는 즉시 하지만 소비자에게 전달하는 시간을 지연시키고,
 * delaySubscription()은 데이터 생성 및 통지 자체를 지연시킨다.
 *
 * 구독 자체를 지연시킨다.
 * 생산자가 데이터의 생성 및 통지 자체를 설정한 시간만큼 지연시킨다.
 * 즉, 소비자가 구독을 해도 구독 시점 자체가 지연된다.
 */
public class _3_ObservableDelaySubscriptionExample {
    public static void main(String[] args) {
        Logger.log(LogType.PRINT, "# 실행 시작 시간: " + TimeUtil.getCurrentTimeFormatted());

        Observable.just(1, 3, 4, 6)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                // 2초 delay
                .delaySubscription(2000L, TimeUnit.MILLISECONDS)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(2500L);
    }
}
