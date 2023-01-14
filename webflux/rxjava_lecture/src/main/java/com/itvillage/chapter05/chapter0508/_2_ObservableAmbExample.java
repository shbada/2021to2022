package com.itvillage.chapter05.chapter0508;

import com.itvillage.common.SampleData;
import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import com.itvillage.utils.TimeUtil;
import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * amb를 이용하여 가장 먼저 통지되는 Observable을 선택하여 통지하는 예제
 *
 * 여러개의 Observable 중에서 최초 통지 시점에 가장 빠른 Observable의 데이터만 통지되고,
 * 나머지 Observable은 무시된다.
 * 즉, 가장 먼저 통지를 시작한 Observable의 데이터만 통지된다.
 */
public class _2_ObservableAmbExample {
    public static void main(String[] args) {

        List<Observable<Integer>> observables = Arrays.asList(
                /* 총 3개의 Observable */
                // 0.2초
                Observable.fromIterable(SampleData.salesOfBranchA)
                        .delay(200L, TimeUnit.MILLISECONDS)
                        .doOnComplete(() -> Logger.log(LogType.DO_ON_COMPLETE, "# branch A's sales")),

                // 0.3초
                Observable.fromIterable(SampleData.salesOfBranchB)
                        .delay(300L, TimeUnit.MILLISECONDS)
                        .doOnComplete(() -> Logger.log(LogType.DO_ON_COMPLETE, "# branch B's sales")),

                // 0.5초
                Observable.fromIterable(SampleData.salesOfBranchC)
                        .delay(500L, TimeUnit.MILLISECONDS)
                        .doOnComplete(() -> Logger.log(LogType.DO_ON_COMPLETE, "# branch C's sales"))
        );

        // doOnComplete() | RxComputationThreadPool-1 | 13:25:53.111 | # branch A's sales
        // 가장 빠른 A 외의 Observable 들은 무시된다.
        Observable.amb(observables)
                .doOnComplete(() -> Logger.log(LogType.DO_ON_COMPLETE, "# 완료"))
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(1000L);
    }
}
