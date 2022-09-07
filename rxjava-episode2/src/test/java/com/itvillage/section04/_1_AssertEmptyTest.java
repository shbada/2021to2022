package com.itvillage.section04;

import com.itvillage.common.Car;
import com.itvillage.section03.SampleObservable;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * testSubscriber / TestObserver
 * (Observer : 배압 기능 없음, Subscriber : 배압 기능 있음)
 *
 * - 테스트 용도로 사용되는 소비자 클래스다.
 * - assertXXX 함수를 이용해 통지된 데이터를 검증할 수 있다.
 * - assertXXX 함수를 이용해 지정된 시간동안 대기하거나 완료 또는 에러 이벤트가 발생할때까지 대기할 수 있다.
 * - 완료, 에러, 구독 해지 등의 이벤트 발생 결과값을 이용해서 데이터를 검증할 수 있다.
 */


/**
 * assertEmpty를 사용하여 해당 시점까지 통지된 데이터가 있는지 검증하는 예제
 *
 * - 테스트 시점까지 통지받은 데이터가 없다면 테스트에 성공한다.
 * - Observable.empty()로 생성시, 완료를 통지하기 때문에 테스트가 실패한다.
 * - 즉, 통지 이벤트 자체가 없는지를 테스트할 수 있다.
 */
public class _1_AssertEmptyTest {
    // 테스트 실패 예제
    @Test
    public void getCarStreamEmptyFailTest(){
        // when
        Observable<Car> observable = SampleObservable.getCarStream(); // 9개
        // test() : 내부적으로 TestObservable 을 생성해서 구독함
        // 구독했으므로, 생산자 쪽에서는 통지한다.
        TestObserver<Car> observer = observable.test();

        // then
        // 통지되는 데이터를 0.1초 동안 지연시킨다.
        // assertEmpty() : 통지 이벤트 자체가 없는지 검증한다.
        observer.awaitDone(100L, TimeUnit.MILLISECONDS).assertEmpty();
    }

    // 테스트 성공 예제
    @Test
    public void getCarStreamEmptySuccessTest(){
        // when
        Observable<Car> observable = SampleObservable.getCarStream();
        // 1초 지연
        TestObserver<Car> observer = observable.delay(1000L, TimeUnit.MILLISECONDS).test();

        // then
        // 0.1초 지연된 시점에는 통지가 안된다. 위에서 1초 지연했으므로
        // 특정 시점동안 통지되는 이벤트가 없으면 성공!
        observer.awaitDone(100L, TimeUnit.MILLISECONDS).assertEmpty();
    }
}
