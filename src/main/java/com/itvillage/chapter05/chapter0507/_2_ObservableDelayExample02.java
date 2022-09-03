package com.itvillage.chapter05.chapter0507;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import com.itvillage.utils.TimeUtil;
import io.reactivex.Observable;

/**
 * 통지되는 데이터 각각에 지연 시간을 적용하는 예제
 *
 * 파라미터로 생성되는 Observable이 데이터를 통지할때까지 각각의 원본 데이터의 통지를 지연시킨다.
 */
public class _2_ObservableDelayExample02 {
    public static void main(String[] args) {
//        Observable.just(1,3,5,7)
//                .flatMap(item -> {
//                    // 각각의 데이터 통지 시간 1초 지연
//                    TimeUtil.sleep(1000L);
//                    return Observable.just(item); // 새로운 Observable의 통지 시점에, 원본 데이터를 통지한다.
//                }).subscribe(data -> Logger.log(LogType.ON_NEXT, data));
        Observable.just(1,3,5,7)
                .delay(item -> {
                    // 각각의 데이터 통지 시간 1초 지연
                    TimeUtil.sleep(1000L);
                    return Observable.just(item); // 새로운 Observable의 통지 시점에, 원본 데이터를 통지한다.
                }).subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
