package com.itvillage.chapter05.chapter0501;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import io.reactivex.Observable;

import java.time.LocalTime;

/**
 * 실제 구독이 발생할 때 Observable을 새로 반환하여 새로운 Observable을 생성한다.
 * defer()를 활용하면 데이터 흐름의 생성을 지연하는 효과를 보여준다.
 */
public class _4_ObservableDeferExample {
    public static void main(String[] args) throws InterruptedException {
        // 구독이 발생할때마다 새로운 Observable 을 생성한다. (즉, subscribe()가 호출될때마다)
        // 호출 시점의 데이터를 통지한다. -> 데이터 생성을 미루는 효과가 있으므로 최신 데이터를 활용할 수 있다.
        Observable<LocalTime> observable = Observable.defer(() -> {
            LocalTime currentTime = LocalTime.now();
            return Observable.just(currentTime);
        });

        Observable<LocalTime> observableJust = Observable.just(LocalTime.now());

        // 첫번째 구독
        observable.subscribe(time -> Logger.log(LogType.PRINT, " # defer() 구독1의 구독 시간: " + time));
        // just()가 선언된 현재 시점
        observableJust.subscribe(time -> Logger.log(LogType.PRINT, " # just() 구독1의 구독 시간: " + time));

        Thread.sleep(3000);

        // 두번째 구독
        // 3초가 지난 시점의 시간 출력 : 구독을 한 시점의 시간
        observable.subscribe(time -> Logger.log(LogType.PRINT, " # defer() 구독2의 구독 시간: " + time));
        // just()가 선언된 현재 시점
        observableJust.subscribe(time -> Logger.log(LogType.PRINT, " # just() 구독자2의 구독 시간: " + time));
    }
}
