package com.itvillage.chapter05.chapter0501;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import io.reactivex.Observable;

/**
 * 반복문으로 사용 가능
 */
public class _2_ObservableRangeExample {
    public static void main(String[] args){
        // 0부터 시작해서 5까지 반복해서 통지
        Observable<Integer> source = Observable.range(0, 5);

        // 구독 - 데이터 출력
        source.subscribe(num -> Logger.log(LogType.ON_NEXT, num));
    }
}
