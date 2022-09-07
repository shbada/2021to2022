package com.itvillage.section04._1_assert;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import io.reactivex.Observable;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * assertNoValues를 이용해 통지 시점까지 통지된 데이터가 없는지 검증하는 예제
 *
 * - 해당 시점까지 통지된 데이터가 없으면 테스트에 성공한다.
 * - 완료 통지와 에러 통지는 테스트 대상에서 제외된다.
 */
public class _4_AssertNoValuesTest {
    @Test
    public void assertNoValuesTest(){
        Observable.interval(200L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> Logger.log(LogType.ON_NEXT, data))
                .filter(data -> data > 5)
                .test()
                .awaitDone(1000L, TimeUnit.MILLISECONDS)
                .assertNoValues();
    }
}
