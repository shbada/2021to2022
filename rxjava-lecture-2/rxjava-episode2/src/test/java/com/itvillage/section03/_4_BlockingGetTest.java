package com.itvillage.section03;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import io.reactivex.Observable;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * blockingGet을 사용한 통지 데이터 테스트 예제
 *
 * - 샌상자쪽에서 통지되는 데이터가 없더라도 완료되는 시점에 소비자에 null 값을 리턴해준다.
 * - 반드시 통지 데이터가 존재하지 않아도된다.
 * - 생산자가 0개 또는 1개의 데이터를 통지하고 완료되면 해당 데이터를 반환한다.
 * - 즉, 생산자가 Maybe일 경우 사용할 수 있다.
 */
public class _4_BlockingGetTest {
    @Test
    public void blockingGetEmptyTest(){
        // then
        assertThat(Observable.empty().firstElement().blockingGet(), is(nullValue()));
    }

    // A 지점의 월간 매출 합계 테스트
    @Test
    public void totalSalesOfBranchATest(){
        // when
        int totalSales = SampleObservable.getSalesOfBranchA()
                // 연속 값 처리 (누적값) . 최종 결괏값 : 최종 누적값을 리턴해준다.
                .reduce((a, b) -> a + b)
                .blockingGet();

        // then
        assertThat(totalSales, is(326_000_000));
    }

    // A, B, C 지점의 연간 매출 합계 테스트
    @Test
    public void salesAllBranchTest(){
        // when
        int totalSales = Observable.zip( // 동일한 인덱스에 있는 데이터들을 결합
                SampleObservable.getSalesOfBranchA(),
                SampleObservable.getSalesOfBranchB(),
                SampleObservable.getSalesOfBranchC(),
                (a, b, c) -> a + b + c // a, b, c의 각 데이터들을 가공 (a + b + c: 전체지점의 월별 매출액 계산)
            )
            .doOnNext(data -> Logger.log(LogType.ON_NEXT, data))
            .reduce((a, b) -> a + b) // 결과적으로 a, b, c지점의 월별 매출액을 누적한 연간 매출액
            .blockingGet();

        // then
        assertThat(totalSales, is(992_000_000));

    }
}
