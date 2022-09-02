package com.itvillage.chapter05.chapter0504;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import io.reactivex.Observable;
import io.reactivex.Single;

import java.util.List;

/**
 * 각각의 통지 데이터를 List로 변환해서 Single로 한번만 통지하는 예제
 *
 * 통지되는 데이터를 모두 List에 담아 통지한다.
 * 원본 Observable에서 완료 통지를 받는 즉시 리스트를 통지한다.
 * 통지되는 데이터는 원본 데이터를 담은 리스트 하나이므로 Single로 반환된다.
 */
public class _5_ObservableToListExample01 {
    public static void main(String[] args) {
        Single<List<Integer>> single = Observable.just(1, 3, 5, 7, 9)
                                                .toList();

        // onNext() | main | 08:14:43.029 | [1, 3, 5, 7, 9]
        single.subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
