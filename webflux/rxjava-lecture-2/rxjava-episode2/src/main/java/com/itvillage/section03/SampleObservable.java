package com.itvillage.section03;

import com.itvillage.common.Car;
import com.itvillage.common.CarMaker;
import com.itvillage.common.SampleData;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class SampleObservable {
    public static Observable<CarMaker> getDuplicatedCarMakerStream() {
        Observable<CarMaker> observable = Observable.fromArray(SampleData.carMakersDuplicated)
                .subscribeOn(Schedulers.computation()); // 별도의 스레드로 생성

        return observable;
    }

    public static Observable<CarMaker> getCarMakerStream() {
        Observable<CarMaker> observable =
                Observable.fromArray(SampleData.carMakers)
                        .subscribeOn(Schedulers.computation());

        return observable;
    }

    public static Observable<Car> getCarStream() {
        return Observable
                .fromIterable(SampleData.carList)
                .subscribeOn(Schedulers.computation());
    }

    public static Observable<Integer> getSalesOfBranchA() {
        return Observable
                .fromIterable(SampleData.salesOfBranchA)
                .subscribeOn(Schedulers.computation());
    }

    public static Observable<Integer> getSalesOfBranchB() {
        return Observable
                .fromIterable(SampleData.salesOfBranchB)
                .subscribeOn(Schedulers.computation());
    }

    public static Observable<Integer> getSalesOfBranchC() {
        return Observable
                .fromIterable(SampleData.salesOfBranchC)
                .subscribeOn(Schedulers.computation());
    }

    public static Observable<Integer> getSpeedOfSectionA() {
        return Observable
                .fromArray(SampleData.speedOfSectionA)
                .subscribeOn(Schedulers.computation());
    }

    public static Observable<Integer> getTemperatureOfSeoul() {
        return Observable
                .fromArray(SampleData.temperatureOfSeoul)
                .subscribeOn(Schedulers.computation());
    }



}
