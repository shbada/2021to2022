package com.itvillage.section02;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import io.reactivex.Observable;

/**
 * onSubscribe 이벤트 발생 전에 호출되는 doOnSubscribe 의 사용 예제
 *
 * - 구독 시작시에, 지정된 작업을 처리할 수 있다.
 * - onSubscribe 이벤트가 발생하기 직전에 실행된다.
 */
public class DoOnSubscribeExample {
    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5, 6, 7)
                // 데이터 통지된 수행될 것이다.
                // 소비자쪽에서 구독을 하고 request를 보내면 생산자쪽에서 onSubscribe 함수를 호출하는데, 이때 doOnSubscribe()가 호출된다.
                // 생산자쪽의 정상적인 소비자 구독 처리가 되었는지 확인하는 용도다.
                .doOnSubscribe(disposable -> Logger.log(LogType.DO_ON_SUBSCRIBE, "# 생산자: 구독 처리 준비 완료"))
                // 소비자
                .subscribe(
                        // 생산자 쪽에서 ON_NEXT 이벤트가 발생했을때 수행되는 함수
                        data -> Logger.log(LogType.ON_NEXT, data),
                        // 생산자 쪽에서 에러가 발생했을때 호출되는 함수
                        error -> Logger.log(LogType.ON_ERROR, error),
                        // 생산자 쪽에서 완료를 통지했을때 호출되는 함수
                        () -> Logger.log(LogType.ON_COMPLETE),
                        // 소비자쪽에서 구독을 하게되면 생산자쪽에 데이터를 통지해달라고 요청을 하는데(request),
                        // 그럼 생산자쪽에서 ON_SUBSCRIBE 함수를 호출하게되면 호출되는 함수
                        dispose -> Logger.log(LogType.ON_SUBSCRIBE, "# 소비자: 구독 처리 준비 완료 알림 받음")
                );
    }
}
