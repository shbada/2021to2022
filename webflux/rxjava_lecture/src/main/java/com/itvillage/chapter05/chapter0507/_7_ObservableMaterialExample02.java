package com.itvillage.chapter05.chapter0507;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import com.itvillage.utils.TimeUtil;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.Arrays;

/**
 * Material/Dematerial 연산자의 실제 활용 예제
 * - 특정 Observable 에서 에러가 발생 할 경우 해당 에러에 대해서 구체적으로 처리할 수 있다.
 */
public class _7_ObservableMaterialExample02 {
    public static void main(String[] args) {
        // concatEager : concat 함수는 여러 Observable 들을 받아서 순차적으로 처리하는데,
        // concatEager 함수는 파라미터로 전달받은 Observable을 동시에 실행한다.(동시 실행 여부가 concat 함수와의 차이다)
        // 소비자 쪽에 전달은 동시에 실행한 데이터를 먼저 입력된 순서대로 통지한다.
        Observable.concatEager(
                Observable.just(
                        // getDBUser (먼저 입력됬으므로 먼저 소비자쪽에 전달된다)
                        getDBUser().subscribeOn(Schedulers.io()), // 각각을 스레드에서 독립적으로 실행
                        // getAPIUser
                        getAPIUser()
                                .subscribeOn(Schedulers.io()) // 각각을 스레드에서 독립적으로 실행
                                /* materialize */
                                .materialize()
                                .map(notification -> {
                                    // 통지된 데이터가 에러인 경우
                                    if (notification.isOnError()) {
                                        // 관리자에게 에러 발생을 알림
                                        // 구체적 에러 처리 가능
                                        Logger.log(LogType.PRINT, "# API user 에러 발생!");
                                    }
                                    return notification;
                                })
                                .filter(notification -> !notification.isOnError())
                                /* dematerialize */
                                .dematerialize(notification -> notification)
                )
        ).subscribe(
                data -> Logger.log(LogType.ON_NEXT, data),
                error -> Logger.log(LogType.ON_ERROR, error),
                () -> Logger.log(LogType.ON_COMPLETE)
        );

        TimeUtil.sleep(1000L);
    }

    private static Observable<String> getDBUser() {
        return Observable.fromIterable(Arrays.asList("DB user1", "DB user2", "DB user3", "DB user4", "DB user5"));
    }

    private static Observable<String> getAPIUser() {
        return Observable
                .just("API user1", "API user2", "Not User", "API user4", "API user5")
                .map(user -> {
                    if (user.equals("Not User")) // 고의 에러 발생
                        throw new RuntimeException();
                    return user;
                });
    }
}
