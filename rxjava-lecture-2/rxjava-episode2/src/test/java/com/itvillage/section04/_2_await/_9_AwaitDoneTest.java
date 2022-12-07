package com.itvillage.section04._2_await;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import io.reactivex.Observable;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * awaitDone 을 이용해서 지정된 시간 또는 완료 통지가 있을때까지 대기하는 예제
 *
 * - 파라미터로 지정된 시간동안 쓰레드를 대기시키거나 지정된 시간 전에 완료/에러 통지가 있다면 통지가 있을때까지만 대기시킨다.
 */
public class _9_AwaitDoneTest {
    // 지정된 시간까지 완료 통지가 없이, 해당 시점까지 전달 받은 데이터의 개수가 맞는지 검증하는 예제
    @Test
    public void awaitDoneTest01() {
        Observable.interval(200L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .take(5) // 총 통지 시간 : 1초
                .doOnComplete(() -> Logger.log(LogType.DO_ON_COMPLETE))
                .doOnError(error -> Logger.log(LogType.DO_ON_ERROR, error.getMessage()))
                .test()
                .awaitDone(500L, TimeUnit.MILLISECONDS) // 0.5초 동안에 완료 통지가 있었나? -> 없었음
                .assertNotComplete() // true
                .assertValueCount(2); // 통지된 데이터 개수가 2개인가? (0.5초까지는 2개가 맞다)
    }

    // 지정된 시간 전에 완료 통지가 있어, 완료 통지 시점까지만 대기하고 전달 받은 데이터의 개수가 맞는지 검증하는 예제
    @Test
    public void awaitDoneTest02() {
        Observable.interval(200L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .take(5)
                .doOnComplete(() -> Logger.log(LogType.DO_ON_COMPLETE))
                .doOnError(error -> Logger.log(LogType.DO_ON_ERROR, error.getMessage()))
                .test()
                .awaitDone(1500L, TimeUnit.MILLISECONDS) // 1.5초
                .assertComplete() // 완료 통지가 있음
                .assertValueCount(5);
    }
}
