package com.itvillage.chapter03.chapter0301;

import io.reactivex.processors.PublishProcessor;

/**
 * 뜨거운 생산자
 * - 생산자는 소비자 수와 상관없이 데이터를 한번만 통지한다.
 * - 즉, 데이터를 통지하는 타임 라인은 하나다.
 * - 소비자는 발생된 데이터를 처음부터 전달 받는게 아니라 구독한 시점에 통지된 데이터들만 전달받을 수 있다.
 */
public class HotPublisherExample {
    public static void main(String[] args){
        PublishProcessor<Integer> processor = PublishProcessor.create();

        // 구독 시작 (이 시점엔 데이터가 없음)
        processor.subscribe(data -> System.out.println("구독자1: " + data));
        processor.onNext(1); // 1, 3을 통지하게되어 위 구독자가 1, 3을 전달받는다.
        processor.onNext(3);
        // 1, 3 은 통지된 이후 시점에

        // 새로운 구독자 출연
        processor.subscribe(data -> System.out.println("구독자2: " + data));
        processor.onNext(5); // 구독자1, 구독자2 모두 5, 7 출력
        processor.onNext(7);

        processor.onComplete();
    }
}
