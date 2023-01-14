package com.itvillage.chapter03.chapter0303;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import com.itvillage.utils.TimeUtil;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Completable 을 사용하여 어떤 작업을 수행한 후, 완료를 통지하는 예제
 *
 * - 데이터를 1건도 통지하지 않고, 완료 또는 에러를 통지한다.
 * - 데이터 통지의 역할 대신에 Completable 내에서 특정 작업을 수행한 후, 해당 처리가 끝났음을 통지하기 위한 역할을 한다.
 * - COmpletableObserver가 대표적이다.
 */
public class _8_CompletableCreateExample {
    public static void main(String[] args) throws InterruptedException {
        Completable completable = Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                // 데이터를 통지하는것이 아니라 특정 작업을 수행한 후, 완료를 통지한다.
                int sum = 0;
                for(int i =0; i < 100; i++){
                    sum += i;
                }
                Logger.log(LogType.PRINT, "# 합계: " + sum);

                emitter.onComplete();
            }
        });

        completable.subscribeOn(Schedulers.computation())
                .subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable disposable) {
                // 아무것도 하지 않음
            }

            @Override
            public void onComplete() {
                Logger.log(LogType.ON_COMPLETE);
            }

            @Override
            public void onError(Throwable error) {
                Logger.log(LogType.ON_ERROR, error);
            }
        });

        TimeUtil.sleep(100L);
    }
}
