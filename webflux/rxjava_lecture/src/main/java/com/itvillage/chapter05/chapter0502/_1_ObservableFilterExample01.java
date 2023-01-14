package com.itvillage.chapter05.chapter0502;

import com.itvillage.common.CarMaker;
import com.itvillage.common.SampleData;
import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import io.reactivex.Observable;

public class _1_ObservableFilterExample01 {
    public static void main(String[] args) {
        Observable.fromIterable(SampleData.carList) // 통지
                .filter(car -> car.getCarMaker() == CarMaker.CHEVROLET) // 통지 데이터 중 filter
                .subscribe(car -> Logger.log(LogType.ON_NEXT, car.getCarMaker() + " : " + car.getCarName()));
    }
}
