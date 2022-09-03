package com.itvillage.chapter05.chapter0507;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import com.itvillage.utils.NumberUtil;
import com.itvillage.utils.TimeUtil;
import io.reactivex.Observable;

/**
 * timeInterval을 이용해서 데이터가 통지되는데 걸린 시간을 통지하는 예제
 *
 * 통지된 데이터와 데이터가 통지되는데 걸린 시간을 소비자쪽에서 모두 처리할 수 있다.
 */
public class _5_ObservableTimeIntervalExample {
    public static void main(String[] args) {
        // 5개 데이터 통지
        Observable.just(1, 3, 5, 7, 9)
                .delay(item -> {
                    // 100 ~ 1000 사이 랜덤한 숫자
                    // 각각 1, 3, 5, 7, 9의 delay 시간은 각각 다르게 가진다.
                    // 해당 시간이 지난후 데이터를 통지한다.
                    TimeUtil.sleep(NumberUtil.randomRange(100, 1000));
                    return Observable.just(item);
                })
                .timeInterval()
                .subscribe(
                        timed -> Logger.log(LogType.ON_NEXT, "# 통지하는데 걸린 시간: " + timed.time() + "\t# 통지된 데이터: " + timed.value())
                );
    }
}
