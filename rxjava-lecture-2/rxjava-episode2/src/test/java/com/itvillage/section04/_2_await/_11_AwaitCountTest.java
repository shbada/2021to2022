package com.itvillage.section04._2_await;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import io.reactivex.Observable;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * awaitCount 를 이용해 지정된 개수만큼 통지가 될 때까지 대기
 *
 * - 파라미터로 지정된 개수만큼 통지될 때까지 쓰레드를 대기시킨다.
 */
public class _11_AwaitCountTest {
    // 지정된 개수만큼 대기하고 완료 통지 유무, 통지된 데이터 개수 및 데이터의 값과 순서를 검증하는 예제
    @Test
    public void awaitCountTest() {
        Observable.interval(200L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .take(5) // 총 통지 시간 : 1초 (5개를 통지해야 완료 통지를 하는데, 3개에 대기하므로 NotComplete)
                .doOnComplete(() -> Logger.log(LogType.DO_ON_COMPLETE))
                .doOnError(error -> Logger.log(LogType.DO_ON_ERROR, error.getMessage()))
                .test()
                .awaitCount(3) // 통지된 데이터가 3개인 동안만 쓰레드를 대기시킨다.
                .assertNotComplete() // true
                .assertValueCount(3) // 3개인가?
                .assertValues(0L, 1L, 2L);
    }
}
