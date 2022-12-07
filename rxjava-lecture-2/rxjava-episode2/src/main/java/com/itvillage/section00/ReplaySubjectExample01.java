package com.itvillage.section00;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import io.reactivex.subjects.ReplaySubject;

/**
 * - 구독 시점에 이미 통지된 데이터가 있다면 이미 통지된 데이터 중에서 최근 통지된 데이터를 지정한 개수만큼 전달 받은 후,
 * 구독 이후에 통지된 데이터를 전달받는다.
 *
 * - 이미 처리가 완료된 이후에 구독하더라도 지정한 개수 만큼의 최근 통지된 데이터를 전달받는다.
 */
public class ReplaySubjectExample01 {
    public static void main(String[] args){
        ReplaySubject<Integer> subject = ReplaySubject.create();
        subject.onNext(3000);
        subject.onNext(2500);

        subject.subscribe(price -> Logger.log(LogType.ON_NEXT, "# 소비자 1 : " + price));
        subject.onNext(3500);

        subject.subscribe(price -> Logger.log(LogType.ON_NEXT, "# 소비자 2 : " + price));
        subject.onNext(3300);

        subject.onComplete();

        subject.subscribe(price -> Logger.log(LogType.ON_NEXT, "# 소비자 3 : " + price));

    }
}
