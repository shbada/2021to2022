package com.itvillage.section04;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import io.reactivex.Observable;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * assertResult를 사용하여 통지 완료 후, 통지된 데이터와 파라미터로 입력된 데이터의 값과 순서가 일치하는지 검증하는 예제
 *
 * - 해당 시점까지 통지를 완료했고, 통지된 데이터와 파라미터로 입력된 데이터의 값과 순서가 같으면 테스트가 성공한다.
 * - assertValues 와의 차이점은 해당 시점까지 완료 통지를 받았느냐 안받았느냐다.
 */
public class _5_AssertResultTest {
    // 테스트 실패 예제
    @Test
    public void assertResultFailTest(){
        // 0.2초마다 데이터 통지
        Observable.interval(200L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> Logger.log(LogType.ON_NEXT, data))
                .filter(data -> data > 3)
                .test()
                .awaitDone(1100L, TimeUnit.MILLISECONDS) // 1.1 초 대기
                .assertResult(4L); // 4가 맞는데, 완료 통지가 되지 않았
    }

    // 테스트 성공 예제
    @Test
    public void assertResultSuccessTest(){
        Observable.interval(200L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> Logger.log(LogType.ON_NEXT, data))
                .take(5) // 5개만큼 데이터를 통지한 후에, 완료 통지를 받는다.
                .filter(data -> data > 3)
                .test()
                .awaitDone(1100L, TimeUnit.MILLISECONDS)
                .assertResult(4L);
    }
}
