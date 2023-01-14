package com.itvillage.section02;

import com.itvillage.common.CarMaker;
import com.itvillage.common.SampleData;
import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import com.itvillage.utils.TimeUtil;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.util.concurrent.TimeUnit;

/**
 * 구독 해지 시 doOnDispose 를 이용하여 지정한 처리를 하는 예제
 *
 * doOnCancel
 * = FLowable 에서 구독 해지가 되는 시점에 호출되는 함수
 * doOnDispose
 * = Observable 에서 구독 해지가 되는 시점에 호출되는 함수
 *
 * = 소비자가 구독을 해지하는 시점에, 지정된 작업을 처리할 수 있다.
 * = 데이터 통지가 완료가 되거나, 에러로 종료될 경우는 실행되지 않는다.
 * (소비자쪽에서 구독을 해지하게되면 완료/에러통지는 소비자쪽에 전달되지 않는다.)
 */
public class DoOnDisposeExample {
    public static void main(String[] args) {
        // carMakers 데이터 전달받고,
        Observable.fromArray(SampleData.carMakers)
                // zipWith : 생산자 쪽의 원본 데이터와 파라미터로 전달되는 Observable에서 통지하는 데이터를 결합
                // 0.3초의 delay 타임을 갖고, num 은 interval 함수에서 통지된 0 부터 시작하는 숫자이고
                // carMaker 만 실제로 통지한다.
                .zipWith(Observable.interval(300L, TimeUnit.MILLISECONDS), (carMaker, num) -> carMaker)
                // 소비자 쪽에서 구독을 해지할때 수행된다.
                .doOnDispose(() -> Logger.log(LogType.DO_ON_DISPOSE, "# 생산자: 구독 해지 완료"))
                .subscribe(new Observer<CarMaker>() {
                    private Disposable disposable;
                    private long startTime;

                    // 구독시 호출
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        // disposable 객체 전달 받음 : 이 객체로 구독 해지를 할 수 있다. 이 변수를 저장해둔다.
                        this.disposable = disposable;

                        // 구독 시작 시간 기록
                        this.startTime = TimeUtil.start();
                    }

                    /**
                     * 데이터가 통지될때마다 수행되는 메서드
                     * @param carMaker
                     *          the item emitted by the Observable
                     */
                    @Override
                    public void onNext(CarMaker carMaker) {
                        Logger.log(LogType.ON_NEXT, carMaker);

                        // 구독 해지
                        // 람다로는 이 과정을 보여줄 수가 없다.
                        if(TimeUtil.getCurrentTime() - startTime > 1000L){
                            Logger.log(LogType.PRINT, "# 소비자: 구독 해지 , 1000L 초과");
                            disposable.dispose(); // 구독 해지를 수행한다.
                        }
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


        TimeUtil.sleep(2000L);
    }
}
