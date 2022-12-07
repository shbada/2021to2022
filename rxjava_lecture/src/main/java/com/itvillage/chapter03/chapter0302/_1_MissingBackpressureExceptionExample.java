package com.itvillage.chapter03.chapter0302;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import com.itvillage.utils.TimeUtil;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class _1_MissingBackpressureExceptionExample {
    public static void main(String[] agrs) throws InterruptedException {
        // RxComputationThreadPool-2
        Flowable.interval(1L, TimeUnit.MILLISECONDS) // interval : 일정한 시간의 주기마다 1L 증가하는 숫자 통지
                // interval 함수에서 데이터를 통지할때 호출되는 콜백함수 (데이터 출력을 확인)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                // 데이터를 처리하는 스레드를 분리
                .observeOn(Schedulers.computation())
                .subscribe( // 구독 (통지 속도 > 구독하여 처리하는 속도가 커지면 MissingBackpressureException 발생)
                        data -> { // 데이터 전달받아 처리 (RxComputationThreadPool-1)
                            Logger.log(LogType.PRINT, "# 소비자 처리 대기 중..");
                            TimeUtil.sleep(1000L);
                            Logger.log(LogType.ON_NEXT, data);
                        },
                        error -> Logger.log(LogType.ON_ERROR, error),
                        () -> Logger.log(LogType.ON_COMPLETE)
                );

        Thread.sleep(2000L);

    }
}
