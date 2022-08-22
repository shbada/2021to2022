package com.itvillage.chapter03.chapter0302;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class _6_HelloRxJavaFlowableCreateExample {
    public static void main(String[] args) throws InterruptedException {
        /**
         * 생산자
         */
        Flowable<String> flowable =
                // FlowableOnSubscribe(), 배압전력 을 파라미터로 전달
                Flowable.create(new FlowableOnSubscribe<String>() {
                    /**
                     * FlowableEmitter : 실제적으로 데이터를 통지하는 역할
                     */
                    @Override
                    public void subscribe(FlowableEmitter<String> emitter) throws Exception {
                        String[] datas = {"Hello", "RxJava!"};
                        for(String data : datas) {
                            // 구독이 해지되면 처리 중단
                            if (emitter.isCancelled())
                                return;

                            // 데이터 통지 -> 소비자 쪽의 .subscribe 내부의 onNext()를 호출함
                            emitter.onNext(data);
                        }

                        // 데이터 통지 완료를 알린다 -> 소비자 쪽의 onComplete() 호출
                        emitter.onComplete();
                    }
                }, BackpressureStrategy.BUFFER); // 구독자의 처리가 늦을 경우 데이터를 버퍼에 담아두는 설정.

        /**
         * 소비자
         */
        flowable.observeOn(Schedulers.computation())
                .subscribe(new Subscriber<String>() { // 구독
                    // 데이터 개수 요청 및 구독을 취소하기 위한 Subscription 객체
                    private Subscription subscription;

                    /**
                     * subscription 를 통해서 구독을 해지하거나 요청할 수 있다.
                     */
                    @Override
                    public void onSubscribe(Subscription subscription) {
                            this.subscription = subscription;
                            // 요청 -> 생산자의 subscribe()가 호출됨
                            this.subscription.request(Long.MAX_VALUE);
                    }

                    /**
                     * emitter.onNext(data); 호출 후 호출됨
                     */
                    @Override
                    public void onNext(String data) {
                        Logger.log(LogType.ON_NEXT, data);
                    }

                    @Override
                    public void onError(Throwable error) {
                        Logger.log(LogType.ON_ERROR, error);
                    }

                    /**
                     * emitter.onComplete(); 호출 후 호출됨
                     */
                    @Override
                    public void onComplete() {
                        Logger.log(LogType.ON_COMPLETE);
                    }
                });

        Thread.sleep(500L);
    }
}
