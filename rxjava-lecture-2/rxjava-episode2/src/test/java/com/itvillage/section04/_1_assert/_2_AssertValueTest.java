package com.itvillage.section04._1_assert;

import com.itvillage.common.CarMaker;
import com.itvillage.section03.SampleObservable;
import io.reactivex.Observable;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * assertValue를 이용한 데이터 검증 예제
 *
 * - 통지된 데이터가 한개인 경우 사용한다.
 * - 즉, 통지된 데이터가 1개뿐이므로 파라미터로 입력된 값과 같다면 테스트에 성공한다.
 */
public class _2_AssertValueTest {
    @Test
    public void assertValueTest(){
        Observable.just("a") // 문자열 a 통지
                .test() // testObservable 객체를 얻고,
                .assertValue("a"); // 검증
    }

    @Test
    public void getCarMakerAssertValueTest(){
        SampleObservable.getCarMakerStream()
                .filter(carMaker -> carMaker.equals(CarMaker.SAMSUNG))
                .test()
                .awaitDone(1L, TimeUnit.MILLISECONDS) // 이 대기시간동안
                .assertValue(CarMaker.SAMSUNG); // 통지되는 데이터가 1개인지 체크
    }
}
