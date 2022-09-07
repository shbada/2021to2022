package com.itvillage.section04;

import com.itvillage.section03.SampleObservable;
import com.itvillage.utils.TimeUtil;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * assertComplete를 이용하여 A 지점과 B 지점의 매출 합계 처리가 지정된 시간안에 끝나는지 검증하는 예제
 *
 * - 해당 시점까지 완료 통지가 있으면 테스트에 성공한다.
 */
public class _7_AssertCompleteTest {
    @Test
    public void assertCompleteTest() {
        SampleObservable.getSalesOfBranchA()
                .zipWith(
                        SampleObservable.getSalesOfBranchB(),
                        (a, b) -> { // 12개의 데이터를 1개씩 합쳐서 전달
                            TimeUtil.sleep(100L); // 0.1초 delay
                            return a + b;
                        }
                )
                .test()
                .awaitDone(3000L, TimeUnit.MILLISECONDS) // 3초 이내에 통지 여부 검증
                .assertComplete();
    }
}
