package com.itvillage.section00;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import io.reactivex.subjects.PublishSubject;

/**
 * 소비자가 구독한 시점 이 후에 통지 된 데이터만 소비자에게 전달되는 PublishSubject 예제
 * (Hot Publisher(뜨거운 생산자))
 *
 * -  Subject는 Reactive Streams의 Processor와 동일한 기능을 하나, 배압 기능이 없는 추상 클래스다.
 *
 * - 구독 전에 통지된 데이터는 받을 수 없고, 구독한 이후에 통지된 데이터만 받을 수 있다.
 * - 데이터 통지가 완료된 이후에 소비자가 구독하면 완료 또는 에러 통지를 받는다.
 */
public class PublishSubjectExample {
    public static void main(String[] args){
        PublishSubject<Integer> subject = PublishSubject.create();

        // 첫번째 구독 발생
        subject.subscribe(price -> Logger.log(LogType.ON_NEXT, "# 소비자 1 : " + price));
        subject.onNext(3500);

        // 두번째 구독 발생
        subject.subscribe(price -> Logger.log(LogType.ON_NEXT, "# 소비자 2 : " + price));
        subject.onNext(3300);

        // 세번째 구독 발생
        subject.subscribe(price -> Logger.log(LogType.ON_NEXT, "# 소비자 3 : " + price));
        subject.onNext(3400);

        /*
            onNext() | main | 18:23:13.400 | # 소비자 1 : 3500
            onNext() | main | 18:23:13.401 | # 소비자 1 : 3300
            // 이미 통지된 3500은 받지 못한다. 구독 시점의 통지 데이터부터 받기 때문이다.
            onNext() | main | 18:23:13.401 | # 소비자 2 : 3300
            onNext() | main | 18:23:13.402 | # 소비자 1 : 3400
            onNext() | main | 18:23:13.402 | # 소비자 2 : 3400
            onNext() | main | 18:23:13.402 | # 소비자 3 : 3400
         */

        // 마지막 (네번째) 구독 발생
        // 더이상 통지될 데이터가 없을 것
        // 완료 통지는 받는다.
        subject.subscribe(
                price -> Logger.log(LogType.ON_NEXT, "# 소비자 4 : " + price),
                error -> Logger.log(LogType.ON_ERROR, error),
                () -> Logger.log(LogType.ON_COMPLETE, "## : ")
        );

        // 완료통지
        subject.onComplete();
    }
}
