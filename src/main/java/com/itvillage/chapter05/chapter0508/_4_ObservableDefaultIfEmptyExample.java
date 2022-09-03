package com.itvillage.chapter05.chapter0508;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import io.reactivex.Observable;

/**
 * 통지되는 데이터가 없을 경우, 디폴트 값을 통지하는 예제
 *
 * 통지할 데이터가 없을 경우 파라미터로 입력된 값을 통지한다.
 * 즉, 연산자 이름 의미 그대로 Observable에 통지할 데이터가 없이 비어있는 상태일때 디폴트 값을 통지한다.
 */
public class _4_ObservableDefaultIfEmptyExample {
    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5)
//                .filter(num -> num > 3)
                .filter(num -> num > 10)
                // 데이터가 없으면 default 값을 통지한다.
                .defaultIfEmpty(10)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
