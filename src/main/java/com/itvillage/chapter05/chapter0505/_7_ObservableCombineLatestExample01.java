package com.itvillage.chapter05.chapter0505;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import com.itvillage.utils.TimeUtil;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

/**
 * - 각 Observable에서 통지할 때 마다 모든 Observable에서 마지막으로 통지한 데이터들을 함수형 인터페이스에 반환하고,
 * 이를 가공해서 통지하는 예제.
 * - 각 Observable 중 하나의 Observable에서만 통지가 발생하더라도 이미 통지한 Observable의 마지막 데이터와
 * 함께 전달된다.
 *
 * 다수의 Observable에서 통지된 데이터를 받아서 다시 하나의 Observable 로 통지한다.
 *
 */
public class _7_ObservableCombineLatestExample01 {
    public static void main(String[] args) {
        Observable<Long> observable1 =
                Observable.interval(500L, TimeUnit.MILLISECONDS)
//                        .doOnNext(data -> Logger.don("# observable 1 : " + data))
                        .take(4);

        Observable<Long> observable2 =
                Observable.interval(700L, TimeUnit.MILLISECONDS)
//                        .doOnNext(data -> Logger.don("# observable 2 : " + data))
                        .take(4);

        /*
            onNext() | RxComputationThreadPool-2 | 17:14:25.086 | data1: 0	data2: 0
            onNext() | RxComputationThreadPool-1 | 17:14:25.381 | data1: 1	data2: 0
            -> 1초가 지난후 data1은 1이 통지됬고, data2는 마지막에 통지된 0 그대로다.
            onNext() | RxComputationThreadPool-2 | 17:14:25.779 | data1: 1	data2: 1
            onNext() | RxComputationThreadPool-1 | 17:14:25.879 | data1: 2	data2: 1
            onNext() | RxComputationThreadPool-1 | 17:14:26.378 | data1: 3	data2: 1
            onNext() | RxComputationThreadPool-2 | 17:14:26.481 | data1: 3	data2: 2
            onNext() | RxComputationThreadPool-2 | 17:14:27.178 | data1: 3	data2: 3
         */
        Observable.combineLatest( // 특정 시점의 최신 데이터만 소비자에 전달할 수 있다.
                observable1,
                observable2,
                (data1, data2) -> "data1: " + data1 + "\tdata2: " + data2)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(3000L);
    }
}
