package com.itvillage.chapter05.chapter0501;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import com.itvillage.utils.TimeUtil;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

/**
 * 설정한 시간이 지난 후에 특정 동작을 수행하고자 할때 사용
 */
public class _3_ObservableTimerExample {
    public static void main(String[] args){
        Logger.log(LogType.PRINT, "# Start!");

        // 지정한 시간이 지나면 0(Long)을 통지한다.
        // 0을 통지하고 onComplete() 이벤트가 발생하여 종료한다.
        Observable<String> observable =
                Observable.timer(2000, TimeUnit.MILLISECONDS)
                        .map(count -> "Do work!"); // 문자열 반환

        observable.subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(3000);
    }
}
