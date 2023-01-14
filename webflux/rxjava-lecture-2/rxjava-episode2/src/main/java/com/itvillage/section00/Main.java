package com.itvillage.section00;

/**
 * 1) Processor란?
 * - Processor는 Reactive Streams에서 정의한 Publisher 인터페이스와 Subscriber 인터페이스를 둘다 상속한 확장 인터페이스
 * - Processor는 Publisher(생산자)의 기능과 Subscriber(소비자)의 기능을 모두 가지고있다.
 * - Processor는 Hot Publisher(뜨거운 생산자)이다.
 *
 * > Cold Publisher(차가운 생산자) : 소비자는 구독할때마다 타임라인의 처음부터 모든 데이터를 받을 수 있다.
 * > Hot Publisher(뜨거운 생산자) : 소비자는 구독한 시점의 타임라인부터 통지된 데이터를 받을 수 있다.
 *
 * 2) Subject란?
 * - Subject는 Reactive Streams의 Processor와 동일한 기능을 하나, 배압 기능이 없는 추상 클래스다.
 *
 * - Proceesor와 Subject의 구현 클래스
 * > PublishProcesor / PublishSubject
 * > AsyncProcessor / AsyncSubject
 * > BehaviorProcessor / BehaviorSubject
 * > ReplayProcessor / ReplaySubject
 */
public class Main {
}
