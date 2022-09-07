package com.itvillage.section04;

import com.itvillage.section03.SampleObservable;
import com.itvillage.utils.TimeUtil;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * assertNotComplete를 이용하여 A 지점과 B 지점의 매출 합계 처리가 지정된 시간안에 끝나지않는지 검증하는 예제
 *
 * - 해당 시점까지 완료 통지가 없으면 테스트에 성공한다.
 */
public class _8_AssertNotCompleteTest {
    @Test
    public void assertNotCompleteTest() {
        SampleObservable.getSalesOfBranchA()
                .zipWith(
                        SampleObservable.getSalesOfBranchB(),
                        (a, b) -> {
                            TimeUtil.sleep(1000L); // 1초 delay
                            return a + b; // (총 12초)
                        }
                )
                .test()
                .awaitDone(3000L, TimeUnit.MILLISECONDS) // 3초 대기시간
                .assertNotComplete();
    }

}
