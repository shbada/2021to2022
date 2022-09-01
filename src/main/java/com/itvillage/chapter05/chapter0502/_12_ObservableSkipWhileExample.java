package com.itvillage.chapter05.chapter0502;

import com.itvillage.common.SampleData;
import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import io.reactivex.Observable;

public class _12_ObservableSkipWhileExample {
    public static void main(String[] args) {
        Observable.fromIterable(SampleData.carList)
                // 최초 "티볼리"를 만나기전까지 건너뛴다. 티볼리를 만난 이후는 유효
                .skipWhile(car -> !car.getCarName().equals("티볼리"))
                .subscribe(car -> Logger.log(LogType.ON_NEXT, car.getCarName()));
    }
}
