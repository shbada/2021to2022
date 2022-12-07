package com.itvillage.section03;

import com.itvillage.common.Car;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * blockingLast를 사용한 통지된 마지막 데이터를 테스트하는 예제
 * - 생산자가 통지한 마지막 데이터를 반환한다.
 * - 통지된 데이터가 없을 경우 NoSuchElementException을 발생시킨다.
 * - 결과를 반환하는 시점이 완료를 통지하는 시점이므로, 완료 통지가 없는 데이터 통지일 경우 사용할 수 없다.
 */
public class _2_BlockingLastTest {
    // Car 리스트 중 마지막 Car 테스트
    @Test
    public void getCarStreamLastTest(){
        // when
        Car car = SampleObservable.getCarStream().blockingLast();
        String actual = car.getCarName();

        // then
        assertThat(actual, is("SM5"));
    }

    // A 지점의 월간 매출액 중 6월 달 매출액 테스트
    @Test
    public void getSalesOfBranchALastTest(){
        // when
        int actual = SampleObservable.getSalesOfBranchA()
                    .take(6)
                    .blockingLast();

        // then
        assertThat(actual, is(40_000_000));
    }
}
