package com.itvillage.chapter05.chapter0508;

import com.itvillage.common.CarMaker;
import com.itvillage.common.SampleData;
import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import io.reactivex.Observable;

/**
 * all을 이용하여 통지된 모든 데이터가 파라미터로 입력된 조건과 일치하는지 판단하는 예제
 *
 * 통지되는 모든 데이터가 설정한 조건에 맞는지를 판단한다.
 * 결괏값을 한번만 통지하면 되기 때문에 true/false 값을 Single로 반환한다.
 * 통지된 데이터가 조건에 맞지 않는다면 이후 데이터는 구독 해지되어 통지되지 않는다.
 */
public class _1_ObservableAllExample {
    public static void main(String[] args) {
        Observable.fromIterable(SampleData.carList)
                .doOnNext(car -> Logger.log(LogType.DO_ON_NEXT, "Car Maker: " + car.getCarMaker() +
                        ", \tCar Name: " + car.getCarName()))
                .map(car -> car.getCarMaker())
                // 모든 데이터가 해당 조건을 만족하면 true, 아니면 false
                // false 가 나온 시점 이후 데이터는 구독 해지되어 통지되지 않는다.
                .all(carMaker -> carMaker.equals(CarMaker.CHEVROLET))
//                .all(CarMaker.CHEVROLET::equals)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
