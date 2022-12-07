package com.itvillage.chapter05.chapter0501;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import com.itvillage.utils.TimeUtil;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

/**
 * polling 용도로 주로 사용.
 * polling : 서버나 클라이언트에서 어떤 특정 요청에 대한 결과값을 가져오기 위해 특정 요청을 반복적으로 수행하는것
 */
public class _1_ObservableIntervalExample {
    public static void main(String[] args){
        System.out.println("# start : " +TimeUtil.getCurrentTimeFormatted());

        // 별도 스레드 : RxComputationThreadPool-1
        Observable.interval(1000L, TimeUnit.MILLISECONDS)
                .map(num -> num + " count")
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data)); // 소비자에서 데이터 출력

        TimeUtil.sleep(3000);
    }
}
