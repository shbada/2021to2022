package com.itvillage.section01;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import com.itvillage.utils.TimeUtil;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class SchedulerSingleExample {
    public static void main(String[] args) {
        Observable<String> observable = Observable.just("1", "2", "3", "4", "5");

        // 새로운 스레드를 생성한다. -> main 이 아닌 RxSingleScheduler
        // 대신, 딱 하나의 스레드만 만든다. main 이 아닌 새로운 스레드를 딱 1개만 만들어서 여러번 구독을 하더라도,
        // 생성된 단 1개의 쓰레드로 순차적으로 수행한다.
        observable.subscribeOn(Schedulers.single())
                // 통지되는 데이터에 ## 붙이기
                .map(data -> "## " + data + " ##")
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        observable.subscribeOn(Schedulers.single())
                // 통지되는 데이터에 $$ 붙이기
                .map(data -> "$$ " + data + " $$")
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(300L);
    }
}
