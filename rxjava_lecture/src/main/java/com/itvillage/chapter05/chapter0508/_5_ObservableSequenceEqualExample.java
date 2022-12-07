package com.itvillage.chapter05.chapter0508;

import com.itvillage.common.CarMaker;
import com.itvillage.common.SampleData;
import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import com.itvillage.utils.TimeUtil;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * sequenceEqual 을 이용하여 두 Observable 의 모든 데이터가 같은지(순서, 데이터 수, 데이터 타입)를 판단하는 예제
 *
 * 두 Observable이 동일한 순서로 동일한 갯수의 같은 데이터를 통지하는지 판단한다.
 * 통지 시점과 무관하게 데이터의 정합성만 판단하므로 통지 시점이 다르더라도 조건이 맞다면 true를 통지한다.
 */
public class _5_ObservableSequenceEqualExample {
    public static void main(String[] args) {
        Observable<CarMaker> observable1 =
                Observable
                        .fromArray(SampleData.carMakers) // 데이터 5개
                        .subscribeOn(Schedulers.computation()) // main 스레드 외 스레드로 수행(RxComputationThreadPool)
                        .delay(carMaker -> {
                            // 0.5초
                            TimeUtil.sleep(500L);
                            return Observable.just(carMaker);
                        }).doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, "# observable1 : " + data));

        Observable<CarMaker> observable2 = // main 스레드
                Observable
                        .fromArray(SampleData.carMakersDuplicated) // 데이터 8개 (최종적으로 sequenceEqual 결과는 false 예상)
                        .delay(carMaker -> {
                            // 1초
                            TimeUtil.sleep(1000L);
                            return Observable.just(carMaker);
                        }).doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, "# observable2 : " + data));

        // 모든 데이터가 같은지(순서, 데이터 수, 데이터 타입)를 판단
        // 같으면 true, 다르면 false
        // 5번째에서 KIA vs CHEVOLET 로 데이터가 달라서 최종적으로 false가 전달되었다.
        Observable.sequenceEqual(observable1, observable2)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
