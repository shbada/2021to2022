package com.itvillage.chapter05.chapter0502;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import com.itvillage.utils.TimeUtil;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

/**
 * 지정한 시간동안 데이터를 계속 발행
 */
public class _7_ObservableTakeExample02 {
    public static void main(String[] args) {
        Observable.interval(1000L, TimeUnit.MILLISECONDS) // 1초에 한번씩 데이터 통지
                .take(3500L, TimeUnit.MILLISECONDS) // 3.5초 범위 내에 있는 데이터만 통지
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(3500L);
    }
}
