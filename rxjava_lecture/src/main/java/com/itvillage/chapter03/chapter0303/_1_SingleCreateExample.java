package com.itvillage.chapter03.chapter0303;

import com.itvillage.utils.DateUtil;
import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.disposables.Disposable;

/**
 * Single 클래스를 사용하여 현재 날짜와 시각을 통지하는 예제
 * - 데이터를 1건만 통지하거나 에러를 통지한다.
 * - 통지 자체가 완료다. 완료 통지가 없다.
 * - 데이터 개수 요청할 필요가 없다.
 * - onNext(), onComplete()가 없다. 이 둘을 합한 onSuccess()를 제공한다.
 * - Single의 대표적인 소비자는 SingleObserver이다.
 * - 클라이언트의 요청에 대응하는 서버의 응답이 Single을 사용하기 좋은 예다.
 *
 */
public class _1_SingleCreateExample {
    public static void main(String[] args){
        Single<String> single = Single.create(new SingleOnSubscribe<String>() {
            /**
             * 하나의 이 메서드를 구현해야한다.
             */
            @Override
            public void subscribe(SingleEmitter<String> emitter) throws Exception {
                emitter.onSuccess(DateUtil.getNowDate());
            }
        });

        single.subscribe(new SingleObserver<String>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                // 아무것도 하지 않음.
                // 단 1건만 통지하므로 추가적으로 데이터를 요청할 필요가 없다.
            }

            @Override
            public void onSuccess(String data) {
                Logger.log(LogType.ON_SUCCESS, "# 날짜시각: " + data);
            }

            @Override
            public void onError(Throwable error) {
                Logger.log(LogType.ON_ERROR, error);
            }
        });
    }
}
