package com.itvillage.chapter03.chapter0302;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import com.itvillage.utils.TimeUtil;
import io.reactivex.BackpressureOverflowStrategy;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;
/**
 * - DROP_LATEST 전략 : 생산자쪽에서 데이터 통지 시점에 버퍼가 가득 차있으면 버퍼내에 있는 데이터 중에서 가장 최근에 버퍼
 * 안에 들어온 데이터를 삭제하고 버퍼 밖에서 대기하는 데이터를 그 자리에 채운다.
 *
 *
*/
public class _2_BackpressureBufferExample01 {
    public static void main(String[] args){
        System.out.println("# start : " +TimeUtil.getCurrentTimeFormatted());
        Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> Logger.log("#inverval doOnNext()", data))
                .onBackpressureBuffer(
                        2, // 버퍼가 수용할 수 있는 데이터 개수 : 2개
                        // (3개 이후 overflow! 발생), 2개가 가장 최근 데이터이므로 삭제됨 :1, 3
                        () -> Logger.log("overflow!"),
                        BackpressureOverflowStrategy.DROP_LATEST) /* DROP_LATEST 전략 */
                // 데이터 처리를 완료하는 시점에 수행
                .doOnNext(data -> Logger.log("#onBackpressureBuffer doOnNext()", data))
                // 소비자에서 전달받은 데이터를 처리하는것을 별도의 스레드로 한다.
                // (bufferSize : 소비자쪽에서 요청하는 데이터의 개수) : 매번 1개를 요청함
                .observeOn(Schedulers.computation(), false, 1)
                .subscribe(
                        data -> {
                            TimeUtil.sleep(1000L);
                            Logger.log(LogType.ON_NEXT, data);
                        },
                        error -> Logger.log(LogType.ON_ERROR, error)
                );

        TimeUtil.sleep(2800L);
    }
}
