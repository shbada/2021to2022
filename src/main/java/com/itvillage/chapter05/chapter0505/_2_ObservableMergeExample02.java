package com.itvillage.chapter05.chapter0505;

import com.itvillage.common.SampleData;
import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import com.itvillage.utils.TimeUtil;
import io.reactivex.Observable;

/**
 * 각 구간의 차량 속도 데이터를 3개의 Observable에서 통지된 순서대로 merge하여 출력하는 예제
 * 통지 시점이 빠른 데이터부터 처리된다.
 */
public class _2_ObservableMergeExample02 {
    public static void main(String[] args) {
        // 55L 마다
        Observable<String> observable1 =
                SampleData.getSpeedPerSection("A", 55L, SampleData.speedOfSectionA);

        // 100L마다
        Observable<String> observable2 =
                SampleData.getSpeedPerSection("B", 100L, SampleData.speedOfSectionB);

        // 77L 마다
        Observable<String> observable3 =
                SampleData.getSpeedPerSection("C", 77L, SampleData.speedOfSectionC);

        Observable.merge(observable1, observable2, observable3)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));


        TimeUtil.sleep(1000L);
    }
}
