package com.itvillage.chapter05.chapter0509;

import com.itvillage.common.SampleData;
import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import io.reactivex.Observable;

/**
 * count를 이용하여 차량의 총 대수를 계산하는 예제
 *
 * 결과값 Single
 * 통지시점 : 모든 데이터가 통지된 이후 
 */
public class _1_ObservableCountExample01 {
    public static void main(String[] args) {
        Observable.fromIterable(SampleData.carList)
                .count()
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
