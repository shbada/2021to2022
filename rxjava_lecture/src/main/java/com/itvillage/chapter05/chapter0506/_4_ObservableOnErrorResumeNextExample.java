package com.itvillage.chapter05.chapter0506;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import com.itvillage.utils.TimeUtil;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

/**
 * onErrorResumeNext를 이용해서 에러 발생시, 다른 Observable로 대체하는 예제.
 *
 * 에러가 발생했을때 에러를 의미하는 Observable로 대체할 수 있다.
 * Observable로 대체할 수 있기 때문에 데이터 교체와 더불어 에러 처리를 위한 추가 작업을 할 수 있다.
 */
public class _4_ObservableOnErrorResumeNextExample {
    public static void main(String[] args) {
        Observable.just(5L) // 5 통지
                .flatMap(num -> Observable
                        // 0.2초마다
                        .interval(200L, TimeUnit.MILLISECONDS)
                        // 5개를 통지
                        .take(5)
                        // i = 0 일때 에러 발생
                        .map(i -> num / i)
                        // 새로운 Observable 객체를 리턴한다.
                        .onErrorResumeNext(throwable -> {
                            Logger.log(LogType.PRINT, "# 운영자에게 이메일 발송: " + throwable.getMessage());
                            // 소비자 쪽에 다시 데이터를 전송한다.
                            // 첫번째 데이터를 생략한다. skip(1)
                            return Observable.interval(200L, TimeUnit.MILLISECONDS).take(5).skip(1).map(i -> num / i);
                        })
                ).subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(2000L);
    }
}
