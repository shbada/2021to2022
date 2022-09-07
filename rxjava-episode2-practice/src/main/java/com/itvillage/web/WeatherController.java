package com.itvillage.web;

import com.itvillage.domain.Weather;
import com.itvillage.repository.WeatherRepository;
import com.itvillage.sensor.HumiditySensor;
import com.itvillage.sensor.TemperatureSensor;
import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 클라이언트의 기상 데이터 요청을 처리하는 Rest API 컨트롤러
 *
 * 클라이언트 측에서 서버 측에 요청을 하면 서버 측에서는 온도/습도 센서의 데이터를 실시간으로 클라이언트에 전송하고,
 * 클라이언트는 서버 측으로부터 전송받은 온/습도 데이터를 실시간으로 웹 브라우저에 표시한다.
 *
 * [요구사항]
 * - 서버 측에서 요청한 온/습도 데이터는 온/습도가 변할때마다 지속적으로 클라이언트에 전송이 되어야한다.
 * - 온/습도 센서로부터 데이터를 가져오는 부분은 서버 측에서 랜덤으로 데이터를 생성하도록 구현한다.
 * - 클라이언트 측에서 [온/습도 측정] 버튼을 누르면 온/습도 데이터가 화면에 지속적으로 표시가 되어야한다.
 * - 클라이언트 측에서 [온/습도 측정 중지] 버튼을 누르면 온/습도 데이터의 표시가 중지되어야한다.
 * - 클라이언트 측에서 [온/습도 측정] 버튼을 누르면 기존에 표시된 온/습도 데이터를 모두 지우고 새롭게 표시할 수 있어야한다.
 *
 * SSE(Server Send Event)란?
 * - 사전적 의미 그대로 새 이벤트가 발생할때마다 서버에서 클라이언트로 데이터를 보내는 HTML 표준 기술이다.
 * - 웹소켓과 같은 양방향 프로토콜이 아니라 서버에서 클라이언트 측으로 데이터를 보내는 단방향 프로토콜이다.
 * - 클라이언트 측에서 서버 측을 폴링할 필요가 없으므로 HTTP 오버 헤드가 적다.
 * (서버에서 데이터 변경이 발생하면 지속적으로 내려주기 때문에)
 * - 서버에서 클라이언트로의 단방향 데이터 전송에는 웹소켓에는 SSE가 적합하다.
 *
 * [실습 프로젝트 동작흐름]
 * 클라이언트 - 서버
 * [온/습도 측정] 버튼 클릭 -> SSE 통신 연결 요청 -> Rest Controller -> 구독/통지 -> 온도센서/습도센서 -> SSE Emitter 에게 데이터 전달
 * SSE Emitter -> (데이터 전송) -> 클라이언트
 */
@RestController
@RequiredArgsConstructor
public class WeatherController {
    final long SSE_SESSION_TIMEOUT = 30 * 60 * 1000L;

    private final TemperatureSensor temperatureSensor;
    private final HumiditySensor humiditySensor;
    private final WeatherRepository weatherRepository;
    private SseEmitter emitter;

    private List<Disposable> disposables = new ArrayList<>();

    /**
     * 클라이언트의 요청을 전달받음
     * (요청 : SSE Connection)
     * @return
     */
    // Server Sent Event를 이용한 HTTP Streaming 연결
    @CrossOrigin("*")
    @GetMapping("/stream/weather")
    public SseEmitter connectWeatherEvents() {
        // SseEmitter 객체 생성 (SSE_SESSION_TIMEOUT : SSE 연결 시간 : 30분동안 연결하겠다는 의미)
        emitter = new SseEmitter(SSE_SESSION_TIMEOUT);

        // 하나의 Weather 객체로 합친다. -> 소비자쪽에 전달될 객체
        ConnectableObservable<Weather> observable =
                Observable.zip(
                    temperatureSensor.getTemperatureStream(), // 온/습도 데이터
                    humiditySensor.getHumidityStream(),       // 온/습도 데이터
                    (temperature, humidity) -> new Weather(temperature, humidity)
                ).publish(); // 구독자들에게 통지되는 데이터를 브로드 캐스팅한다.
        // publish() : Observable 클래스를 ConnectableObservable로 변환해준다.
        // ConnectableObservable 는 Hot Publisher 으로, 구독을 하더라도 connect() 호출전엔 데이터를 통지하지 않는다.

        // WeatherData를 클라이언트로 전달 (Disposable 는 구독해지가 가능한 객ㅊ페)
        Disposable disposableSend = sendWeatherData(observable);
        // WeatherData를 DB에 저장
        Disposable disposableSave = saveWeatherData(observable);

        disposables.addAll(Arrays.asList(disposableSend, disposableSave));

        // conenct() 호출 전까지는 구독을 했음(sendWeatherData(), saveWeatherData())에도 데이터 통지가 안된다.
        // publicsh() 함수게 여러개의 구독자들에게 통지되는 데이터를 브로드캐스트한다.
        // 브로드캐스트란, 통지된 데이터는 한번만 처리를 하고, 이를 여러 구독자들에게 통지하는 것이다.

        observable.connect(); // 이 시점부터 데이터 통지 가능

        this.dispose(emitter, () ->
                disposables.stream()
                .filter(disposable -> !disposable.isDisposed()) // 실제로 구독해지가 되지 않고 통지가 되고있는 객체들만 필터링
                .forEach(Disposable::dispose)); // 구독취소
        return emitter;
    }

    private Disposable sendWeatherData(ConnectableObservable<Weather> observable) {
        // observable로 구독
        return observable.subscribe(
                weather -> {
                    // 전달받은 데이터를 emitter 를 통해 클라이언트에 전달한다.
                    emitter.send(weather);

                    Logger.log(LogType.ON_NEXT,
                            weather.getTemperature() + ", " + weather.getHumidity());
                },
                error -> Logger.log(LogType.ON_ERROR, error.getMessage()));
    }

    private Disposable saveWeatherData(ConnectableObservable<Weather> observable) {
        // observable로 구독
        return observable.subscribe(
                weather -> {
                    // weather 를 지속적으로 저장
                    weatherRepository.save(weather);
                },
                error -> Logger.log(LogType.ON_ERROR, error.getMessage()));
    }

    /**
     * 구독 해지
     * @param emitter
     * @param runnable 구현해야하는 하나의 함수만 가지고있으므로 함수형 인터페이스
     */
    private void dispose(SseEmitter emitter, Runnable runnable){
        emitter.onCompletion(runnable); // SSE 통신의 연결이 종료가 되면 수행
        emitter.onTimeout(runnable); // SSE 통신의 타임아웃 이벤트가 발생하면 수행
    }
}
