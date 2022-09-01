package com.itvillage.chapter05.chapter0503;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import io.reactivex.Observable;

/**
 * FlatMap 두번째 유형을 이용한 구구단의 2단 출력 예제
 * 원본 데이터 + 변환된 데이터를 조합해서 = 새로운 데이터를 통지(최종데이터)
 */
public class _5_ObservableFlatMapExample03 {
    public static void main(String[] args) {
        Observable.range(2, 1)
                .flatMap(
                        // 1차적 변환
                        data -> Observable.range(1, 9),
                        // 두번째 파라미터 : sourceData(통지데이터), transformedDate(위에서 첫번째로 변환된 데이터) 를 다시 가공
                        (sourceData, transformedData) ->
                                sourceData + " * " + transformedData + " = " + sourceData * transformedData
                )
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

    }
}
