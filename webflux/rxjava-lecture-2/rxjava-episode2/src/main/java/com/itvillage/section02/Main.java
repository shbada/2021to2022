package com.itvillage.section02;

/**
 * RxJava 프로그래밍은 데이터를 생성 및 통지하고 이를 구독하여 처리하는 과정이 하나의 문장으로 되어있다.
 * 즉, RxJava 프로그래밍은 선언적 프로그래밍 방식이기 때문에 데이터의 상태 변화를 확인하기 위한 디버깅이 쉽지 않다.
 * RxJava 프로그래밍은 여러 쓰레드가 동시에 실행되는 비동기 프로그래밍이기 때문에 실행시, 항상 같은 결과가 나온다는 보장을 할수가 없다.
 *
 * - 이러한 문제점을 해결하기 위해 RxJava에서는 doXXX로 시작하는 함수를 통해 생산자나 소비자쪽에서 이벤트 발생시, 로그를 기록할 수 있는
 * 방법을 제공한다.
 *
 * - 함수형 프로그래밍의 특성상 부수 효과는 소비자쪽에서 처리하는 것이 맞지만 doXXX 함수는 예외이다.
 * (부수효과란? 어떤 함수에 파라미터를 전달하고 함수를 호출했을때 리턴값이 있다면 부수효과가 없다고 본다.
 * 만약 리턴값이 없고 내부적으로 처리하는 경우 부수효과가 있다.
 * doXXX 함수는 로그를 출력하기 때문에 부수효과를 가지고 있다.)
 *
 * - 따라서 소비자가 전달 받은 데이터를 처리하기 전 원본 데이터의 상태나 변환 및 필터링 등으로 가공되는 시점의 데이터 상태를 doXXX 함수를 통해
 * 쉽게 파악할 수 있다.
 *
 *
 * (추가 doXXX 함수)
 * 1) doAfterNext : 생산자가 통지한 데이터가 소비자에 전달된 직후 호출되는 함수
 * (doOnNext: 소비자에 데이터가 전달되기 전에 호출되는 함수)
 * 2) doOnTerminate : 완료 또는 에러가 통지될때 호출되는 함수 (doOnComplete + doOnError)
 * 3) doAfterTerminate : 완료 또는 에러가 통지된 후 호출되는 함수 (after doOnComplete + doOnError)
 * 4) doFinally : 구독이 취소된 후, 완료 또는 에러가 통지된 후 호출되는 함수
 * (doOnDispose / doOnCancel + doOnComplete + doOnError)
 * (자바에서 try~finally 에서 finally와 비슷하게 모든 구문에서 무조건 호출되는 함수라고 이해하자.)
 * 5) doOnLifeCycle : 소비자가 구독할때 또는 구독을 해지하는 시점에 호출되는 함수
 * (doOnSubscribe + doOnDispose/doOnCancel)
 */
public class Main {
}
