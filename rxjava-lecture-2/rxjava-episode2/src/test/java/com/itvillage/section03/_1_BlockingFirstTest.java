package com.itvillage.section03;

import com.itvillage.common.Car;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * blockingFirst를 사용한 통지된 첫번째 데이터를 테스트하는 예제
 *
 * - 생산자가 통지한 첫번째 데이터를 반환한다.
 * - 통지된 데이터가 없을 경우 NoSuchElementException을 발생시킨다.
 */
public class _1_BlockingFirstTest {
    // Car 리스트 중에서 첫번째 Car를 테스트
    @Test
    public void getCarStreamFirstTest(){
        // when
        // 별도의 쓰레드가 수행된 getCarStream 메서드에서 carList의 첫번째 객체를 리턴받는다.
        Car car = SampleObservable.getCarStream().blockingFirst();
        String actual = car.getCarName();

        // then
        assertThat(actual, is("말리부"));
    }

    @Test
    public void getSalesOfBranchAFirstTest(){
        // when
        int actual = SampleObservable.getSalesOfBranchA()
                    .take(6) // 통지되는 12개 데이터 중에서 6개만 받는다.
                    .blockingFirst(); // 통지되는 첫번째 데이터만 받는다.

        // then
        assertThat(actual, is(15_000_000));
    }
}
