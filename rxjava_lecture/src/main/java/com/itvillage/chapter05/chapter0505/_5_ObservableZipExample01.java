package com.itvillage.chapter05.chapter0505;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import com.itvillage.utils.TimeUtil;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

/**
 * zip을 이용해 2개의 Observable이 통지하는 데이터 중에서 통지되는 순서가 일치하는 데이터들을 조합하는 예제
 *
 * 다수의 Observable에서 통지된 데이터를 받아서 다시 하나의 Observable 로 통지한다.
 * 각 Observable에서 통지된 데이터가 모두 모이면 각 Observable에서 동일한 index의 데이터로 새로운 데이터를 생성한 후 통지한다.
 * 통지하는 개수가 가장 적은 Observable의 통지 시점에 완료 통지 시점을 맞춘다.
 */
public class _5_ObservableZipExample01 {
    public static void main(String[] args) {
        // 0.2초
        Observable<Long> observable1 =
                Observable.interval(200L, TimeUnit.MILLISECONDS)
                        .take(4); // 0, 1, 2, 3

        // 0.4초
        Observable<Long> observable2 =
                Observable.interval(400L, TimeUnit.MILLISECONDS)
                        .take(6); // 0, 1, 2, 3, 4, 5, 6

        // 0, 2, 4, 6
        Observable.zip(observable1, observable2, (data1, data2) -> data1 + data2)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(3000L);
    }
}
