package com.itvillage.chapter05.chapter0508;

import com.itvillage.common.CarMaker;
import com.itvillage.common.SampleData;
import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import io.reactivex.Observable;

/**
 * 통지된 데이터 중에 파라미터로 입력한 조건에 맞는 데이터가 있는지 판단하는 예제
 *
 * 파라미터의 데이터가 Observable에 포함되어 있는지를 판단한다.
 * 결과값을 한번만 통지하면 되기 때문에 true/false 값을 Single로 반환한다.
 * 결과 통지 시점은 Observable에 포함된 데이터를 통지하거나 완료를 통지할때이다.
 */
public class _3_ObservableContainsExample {
    public static void main(String[] args) {
        Observable.fromArray(SampleData.carMakersDuplicated)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                // 일치하는 데이터가 존재할 경우 true, 아닐 경우 false
                .contains(CarMaker.SAMSUNG)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
