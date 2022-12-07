package com.itvillage.section03;

import com.itvillage.common.CarMaker;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

/**
 * RxJava의 API를 사용하지 않은 Unit Test 예제
 */
public class UnitTestNotByRxJava {
    @Test
    public void getCarMakerStreamSyncTest(){
        List<CarMaker> carMakerList = new ArrayList<>();

        // 일반적인 패턴 사용
        // 이 테스트를 실행하면 결과가 어떻게 될까?
        // carMakersList 에 하나씩 데이터를 추가한다.
        // 예상 결과값 : 5개의 리스트
        // -> 실패한다.
        // 예상되는 기대값은 5로 지정했는데, 실제로 carMakerList의 size는 0이다.
        // 왜?
        // getCarMakerStream()을 보면, subscribeOn을 사용해서 데이터를 통지하는 생산자에서 쓰레드를 별도로 지정했다.
        // 그러므로 현재 쓰레드인 main 쓰레드에서는 예상한 결과값이 나오지 못한다.
        // 테스트는 BlockingXXX  함수를 사용해서 호출 대상 쓰레드의 결과값을 검증하도록 해야한다.
        SampleObservable.getCarMakerStream()
                .subscribe(data -> carMakerList.add(data));

        assertThat(carMakerList.size(), is(5));
    }
}
