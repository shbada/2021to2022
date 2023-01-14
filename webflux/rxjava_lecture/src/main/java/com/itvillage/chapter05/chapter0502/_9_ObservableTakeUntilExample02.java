package com.itvillage.chapter05.chapter0502;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import com.itvillage.utils.TimeUtil;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

/**
 * 파라미터로 받은 Flowable/Observable이 최초로 데이터를 발행할 때까지 계속 데이터를 발행
 * timer와 함께 사용하여 특정 시점이 되기전까지 데이터를 발행하는데 활용하기 용이
 */
public class _9_ObservableTakeUntilExample02 {
    public static void main(String[] args) {
        // 1초에 한번씩
        Observable.interval(1000L, TimeUnit.MILLISECONDS)
                // 새로운 Observable 을 파라미터로 받아서 이 Observable이 최초의 데이터를 통지할때까지
                // 데이터를 계속 통지한다.
                // 5.5 초 에 최초의 데이터를 통지하므로 실제로 interval 함수에서는 5초까지 통지한 데이터가 소비자에게 전달되겠다.
                .takeUntil(Observable.timer(5500L, TimeUnit.MILLISECONDS))
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(5500L);
    }
}
