package com.itvillage.chapter03.chapter0303;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.disposables.Disposable;

/**
 * Maybe 클래스를 이용하여 데이터를 통지하는 예제
 *
 * 데이터를 1건만 통지하거나 1건도 통지하지 않고 완료 또는 에러를 통지한다.
 * 1건도 통지않고 종료되는 경우에만 완료 통지를 한다.
 * MaybeObserver - 대표적인 소비자
 */
public class _4_MaybeCreateExample {
    public static void main(String[] args){
        Maybe<String> maybe = Maybe.create(new MaybeOnSubscribe<String>() {
            @Override
            public void subscribe(MaybeEmitter<String> emitter) throws Exception {
//                emitter.onSuccess(DateUtil.getNowDate()); // 데이터 한건 통지

                emitter.onComplete();
            }
        });

        /**
         * onNext 가 없고, 대신 onSuccess가 동일한 역할을 한다.
         */
        maybe.subscribe(new MaybeObserver<String>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                // 아무것도 하지 않음.
            }

            /**
             * 데이터 1건을 전달 받았다면, onComplete()가 호출되지않는다.
             * 데이터 통지 없이 완료 통지가 됬을때 onComplete()가 호출된다.
             */
            @Override
            public void onSuccess(String data) {
                Logger.log(LogType.ON_SUCCESS, "# 현재 날짜시각: " + data);
            }

            @Override
            public void onError(Throwable error) {
                Logger.log(LogType.ON_ERROR, error);
            }

            @Override
            public void onComplete() {
                Logger.log(LogType.ON_COMPLETE);
            }
        });
    }
}
