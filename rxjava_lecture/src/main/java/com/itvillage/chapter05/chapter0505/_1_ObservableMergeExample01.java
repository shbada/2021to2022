package com.itvillage.chapter05.chapter0505;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import com.itvillage.utils.TimeUtil;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

/**
 * 두개 이상의 Observable을 merge하여 통지된 시간 순대로 출력하는 예제
 * 다수의 Observable에서 통지된 데이터를 받아서 다시 하나의 Flowable/Observable로 통지한다.
 * 통지 시점이 같을 경우 merge()함수의 파라미터로 먼저 지정된 Observable의 데이터부터 통지된다.
 */
public class _1_ObservableMergeExample01 {
    public static void main(String[] args) {
        Observable<Long> observable1 = Observable.interval(200L, TimeUnit.MILLISECONDS)
                .take(5);

        Observable<Long> observable2 = Observable.interval(400L, TimeUnit.MILLISECONDS)
                .take(5)
                .map(num -> num + 1000);

        // merge 수행
        Observable.merge(observable1, observable2)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(4000);
    }
}
