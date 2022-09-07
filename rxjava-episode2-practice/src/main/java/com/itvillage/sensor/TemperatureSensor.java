package com.itvillage.sensor;

import com.itvillage.utils.NumberUtil;
import com.itvillage.utils.TimeUtil;
import io.reactivex.Observable;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 온도 센서
 * - 실제 온도 센서에서 온도 데이터를 랜덤한 시간에 가져오도록 시뮬레이션 한다.
 * TODO HumiditySensor와 공통 되는 부분 리팩토링 필요
 */
@Component
public class TemperatureSensor {

    // 온도 데이터를 통지하는 스트림을 생성한다.
    public Observable<Integer> getTemperatureStream(){
        return Observable.interval(0L, TimeUnit.MILLISECONDS) // 0부터 시작
                .delay(item -> {
                    // 통지 대기시간
                    TimeUtil.sleep(NumberUtil.randomRange(1000, 3000)); // 1초~3초 사이에 랜덤
                    return Observable.just(item); // 이 변수값(item)을 실질적으로 사용하지 않는다.
                })
                .map(notUse -> this.getTemperature()); // Map 함수로 전달 (-10 ~ 30 랜덤 숫자)
    }

    private int getTemperature() {
        return NumberUtil.randomRange(-10, 30);
    }
}
