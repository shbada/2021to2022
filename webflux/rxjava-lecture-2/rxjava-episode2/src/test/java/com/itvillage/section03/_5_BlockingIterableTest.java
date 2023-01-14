package com.itvillage.section03;

import com.itvillage.common.CarMaker;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
/**
 * blogkingIterable을 사용한 통지 데이터 테스트 예제
 *
 * - 생산자가 통지한 모든 데이터를 받을 수 있는 Iterable을 얻게한다.
 * - 구독후, Iterable의 next() 메서드를 호출하는 시점부터 처리한다.
 */
public class _5_BlockingIterableTest {
    // 전체 CarMaker의 요소가 맞는지 모두 테스트한다.
    @Test
    public void getCarMakerIterableTest() {
        // when
        Iterable<CarMaker> iterable = SampleObservable.getCarMakerStream()
                .blockingIterable();

        Iterator<CarMaker> iterator = iterable.iterator();

        // then
        assertThat(iterator.hasNext(), is(true));

        // 각각에 맞게 다른 조건으로 검증할 수 있다.
        // 통지된 데이터 자체를 검증
        assertThat(iterator.next(), is(CarMaker.CHEVROLET));
        assertThat(iterator.next(), is(CarMaker.HYUNDAE));
        assertThat(iterator.next(), is(CarMaker.SAMSUNG));
        assertThat(iterator.next(), is(CarMaker.SSANGYOUNG));
        assertThat(iterator.next(), is(CarMaker.KIA));

    }
}
