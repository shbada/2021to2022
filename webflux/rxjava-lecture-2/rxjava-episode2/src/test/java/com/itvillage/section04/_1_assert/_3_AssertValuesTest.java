package com.itvillage.section04._1_assert;

import com.itvillage.common.CarMaker;
import com.itvillage.section03.SampleObservable;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * assertValues를 이용하여 조건에 맞는 1개 이상의 데이터의 값과 순서과 일치하는지 검증하는 예제
 *
 * - 통지된 데이터가 한개인 경우 사용한다.
 * - 즉, 통지된 데이터의 값과 순서가 파람비터로 입력된 데이터의 값과 순서와 일치하면 테스트에 성공한다.
 */
public class _3_AssertValuesTest {
    @Test
    public void getCarMakerAssertValueTest(){
        SampleObservable.getDuplicatedCarMakerStream()
                .filter(carMaker -> carMaker.equals(CarMaker.CHEVROLET))
                .test()
                .awaitDone(1L, TimeUnit.MILLISECONDS)
                // 생산자 쪽에서 통지한 데이터가 1개 이상일때 값-순서의 일치 여부를 체크
                .assertValues(CarMaker.CHEVROLET, CarMaker.CHEVROLET);
    }
}
