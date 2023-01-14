package com.itvillage.section03;

import org.junit.BeforeClass;

/**
 * 단위 테스트를 설명하기 위한 Sample Observable 클래스
 *
 * - 비동기 처리 결과를 테스트하려면 현재 쓰레드에서 호출 대상 쓰레드의 실행 결과를 반환 받을때까지 대기할 수 있어야한다.
 * - RxJava에서는 현재 쓰레드에서 호출 대상 쓰레드의 처리 결과를 받을 수 있는 blockingXXX 함수를 제공한다.
 * - Observable에서 통지되고 가공 처리된 결과 데이터를 현재 쓰레드에 반환하므로, 반환된 결과 값과 예상되는 기대값을 비교해서
 * 단위테스트를 수행할 수 있다.
 */
public class RxJavaTest {
    protected static SampleObservable sampleObservable;

    @BeforeClass
    public static void setup(){
        sampleObservable = new SampleObservable();
    }
}
