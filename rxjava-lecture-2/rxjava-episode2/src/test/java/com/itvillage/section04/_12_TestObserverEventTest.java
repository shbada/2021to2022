package com.itvillage.section04;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import io.reactivex.Observable;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class _12_TestObserverEventTest {
    // 완료 통지 이벤트가 발생해서 종료 되었는지를 검증하는 예제
    @Test
    public void isTerminalEventTest(){
        boolean result = Observable.interval(200L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .take(5) // 5개 데이터 통지받는다.
                .doOnComplete(() -> Logger.log(LogType.DO_ON_COMPLETE))
                .doOnError(error -> Logger.log(LogType.DO_ON_ERROR, error.getMessage()))
                .test()
                .awaitCount(5) // 5개 데이터를 받을때까지만 대기, 5개 이후에 완료를 통지
                .isTerminated(); // 완료 또는 에러 이벤트가 발생할 경우에 true를 반환한다.

        assertThat(result, is(true)); // true
    }

    // 에러 통지 이벤트가 발생해서 종료 되었는지를 검증하는 예제
    @Test
    public void isErrorEventTest(){
        // take 가 없으므로 이건 0.2초마다 무한정 데이터를 통지함
        boolean result = Observable.interval(200L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .map(data -> {
                    if(data == 2) // 고의 에러 발생 (3번째 데이터가 통지될때 에러 발생)
                        throw new RuntimeException("Error happened");
                    return data;
                })
                .doOnComplete(() -> Logger.log(LogType.DO_ON_COMPLETE))
                .doOnError(error -> Logger.log(LogType.DO_ON_ERROR, error.getMessage()))
                .test()
                .awaitCount(5) // 5개 데이터가 통지될때까지 대기한다 했지만, 3번째때 에러가 발생했기 떄문에,
                .isTerminated(); // 에러 이벤트가 발생했으므로 true

        assertThat(result, is(true));
    }
}
