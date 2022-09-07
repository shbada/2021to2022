package com.itvillage.section03;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * blockingSubscribe를 사용해 구독 후 소비자의 처리로 인해 부수 작용이 발생한 결과를 테스트하는 예제
 *
 * - 통지된 원본 데이터를 호출한 원본 쓰레드(현재 쓰레드)에서 부수적인 처리를 할수 있도록 해준다.
 * - 소비자가 전달 받은 데이터로 어떤 부수적인 처리할때 이 처리 결과를 테스트할 수 있다.
 */
public class _7_BlockingSubscribeTest {

    // A 지점의 월간 매출 합계를 부수 작용으로 테스트
    @Test
    public void avgTempOfSeoulTest() {
        Calculator calculator = new Calculator();

        // 통지되는 데이터를 전달받아서, 어떤 추가적인 작업을 한 후에,
        // 이 작업 결과를 검증한다.
        SampleObservable.getSalesOfBranchA()
                .blockingSubscribe(data -> calculator.setSum(data));

        assertThat(calculator.getSum(), is(326_000_000));
    }
}
