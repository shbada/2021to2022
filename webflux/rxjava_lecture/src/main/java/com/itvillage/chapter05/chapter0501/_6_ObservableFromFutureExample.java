package com.itvillage.chapter05.chapter0501;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import com.itvillage.utils.TimeUtil;
import io.reactivex.Observable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Future
 * 비동기 처리를 위한 API
 * 처리 시간이 오래 걸리는거는 Future에 맡기고, 처리 시간이 짧게 걸리는 작업을 동시에 수행 가능 (전체 수행시간 감소)
 */
public class _6_ObservableFromFutureExample {
    /*
        print() | main | 23:41:28.616 | # start time
        print() | ForkJoinPool.commonPool-worker-9 | 23:41:28.618 | # 긴 처리 시간이 걸리는 작업 중.........
        print() | main | 23:41:31.624 | # 짧은 처리 시간 작업 완료!
        print() | main | 23:41:34.621 | # 긴 처리 시간 작업 결과 : 1.0E17
        print() | main | 23:41:34.622 | # end time
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Logger.log(LogType.PRINT, "# start time");

        // 긴 처리 시간이 걸리는 작업
        Future<Double> future = longTimeWork();

        // 짧은 처리 시간이 걸리는 작업
        shortTimeWork();

        // 작업이 완료되면 구독 로직 수행
        Observable.fromFuture(future)
                .subscribe(data -> Logger.log(LogType.PRINT, "# 긴 처리 시간 작업 결과 : " + data));

        Logger.log(LogType.PRINT, "# end time");
    }



    public static CompletableFuture<Double> longTimeWork(){
        return CompletableFuture.supplyAsync(() -> calculate());
    }

    private static Double calculate() {
        Logger.log(LogType.PRINT, "# 긴 처리 시간이 걸리는 작업 중.........");
        TimeUtil.sleep(6000L);
        return 100000000000000000.0;
    }

    private static void shortTimeWork() {
        TimeUtil.sleep(3000L);
        Logger.log(LogType.PRINT, "# 짧은 처리 시간 작업 완료!");
    }
}
