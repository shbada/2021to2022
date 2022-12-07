package com.itvillage.section02;


import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import io.reactivex.Observable;

/**
 * 데이터 통지 시 마다 실행되는 doOnNext 를 이용해 데이터의 상태를 확인하는 예제
 *
 * - 생산자가 데이터를 통지하는 시점에 , 지정된 작업을 처리할 수 있다.
 * - onNext 이벤트가 발생하기 직전에 실행된다.
 * - 통지된 데이터가 함수형 인터페이스의 파라미터로 전달되므로 통지 시점마다 데이터의 상태를 확인할 수 있다.
 */
public class DoOnNextExample {
    public static void main(String[] args) {
        Observable.just(1, 3, 5, 7, 9, 10, 11, 12, 13)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, "# 원본 통지 데이터: " + data))
                .filter(data -> data < 10)
                // 10보다 작은 데이터가 아니라면 doOnNext 수행이 안된다.
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, "# filter 적용 후: " + data))
                .map(data -> "#### " + data + " ####")
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, "# map 적용 후: " + data))
                .subscribe(data -> Logger.log(LogType.ON_NEXT, "# 최종 데이터: " + data));
    }
}
