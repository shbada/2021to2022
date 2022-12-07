package com.itvillage.chapter05.chapter0507;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import io.reactivex.Observable;

/**
 * materialize : 통지된 데이터와 통지된 데이터의 통지 타입 자체를 Notificastion 이라는 객체에 담아서
 * 이 Noticiation 객체를 통지한다.
 * 즉, 통지 데이터의 메타 데이터를 포함해서 통지한다고 볼 수 있다.
 *
 * dematerialize : 통지된 Notification 객체를 원래의 통지 데이터로 변환해서 통지한다.
 *
 */
public class _6_ObservableMaterialExample01 {
    public static void main(String[] args) {
        // 1 ~ 6 까지 숫자 6개 통지
        Observable.just(1, 2, 3, 4, 5, 6)
                // 숫자 6개를 포함해서, 데이터 통지 타입까지 담는다.
                .materialize()
                // Notificatfion 객체를 전달받는다.
                .subscribe(notification -> {
                    // 소비자쪽에서는 isOnNext(), isOnError(), isOnCompleted()를 통해서 통지 타입을 확인할 수 있다.
                    String notificationType =
                            notification.isOnNext() ? "onNext()" : (notification.isOnError() ? "onError()" : "onComplete()");
                    Logger.log(LogType.PRINT, "notification 타입: " + notificationType); // 통지 데이터 타입
                    Logger.log(LogType.ON_NEXT, notification.getValue()); // 통지 데이터
                });
    }
}
