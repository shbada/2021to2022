package com.itvillage.section03;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * blockingSingle을 사용한 통지된 첫번째 데이터를 테스트하는 예제
 *
 * - 생산자가 한개의 데이터를 통지하고 완료되면 해당 데이터를 반환한다.
 * - 2개 이상의 데이터를 통지할 경우에는 IllegalArgumentExcpetion을 발생시킨다.
 */
public class _3_BlockingSingleTest {

    // A 지점의 월간 매출 중에 30,000,000원 이상인 매출액의 첫번째 데이터를 테스트
    @Test
    public void totalSalesOfBranchATest(){
        int actual = SampleObservable.getSalesOfBranchA()
                            .filter(sale -> sale > 30_000_000) // 5개
                            .take(1) // filter 처리된 데이터 중 첫번째 데이터
                            .blockingSingle();

        assertThat(actual, is(35_000_000));
    }

    // A 지점의 월간 매출 중에 30,000,000원 이상인 매출액의 첫번째 데이터를 테스트
    @Test(expected = IllegalArgumentException.class) // 2개 이상을 통지하는 경우 예외 발생
    public void totalSalesOfBranchATest2(){
        SampleObservable.getSalesOfBranchA()
                .filter(sale -> sale > 30_000_000)
                .take(2) // filter 처리된 데이터 중 2개의 데이터
                .blockingSingle(); // 2개 이상을 통지하는 경우 예외 발생
    }
}
