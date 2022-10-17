package com.concurrency.chapter05.예제5_8_프로듀서_컨슈머패턴_데스크탑_예제;

/**
 * 블로킹큐(blocking queue)는 put과 take라는 핵심 메서드를 가지고있다.
 * offer, pool이라는 메서드도 있다.
 * 만약 큐가 가득 차있다면 pull 메서드는 값을 추가할 공간이 생길때까지 대기한다.
 * 반대로 큐가 비어있는 상태라면 take 메서드는 뽑아낼 값이 들어올때까지 대기한다.
 * 큐의 크기에 제한을 두지 않으면 항상 여유 공간이 있는 셈이므로 put 연산이 대기 상태에 들어가는 일이 발생하지 않는다.
 */
public class Main {
}
