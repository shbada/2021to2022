package com.itvillage.chapter05.chapter0504;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import io.reactivex.Observable;
import io.reactivex.Single;

import java.util.Map;

/**
 * 통지된 데이터에서 map의 키를 생성한 후, 각각의 키별로 원본 통지 데이터를 매핑해서 Map으로 반환하는 예제
 *
 * 통지되는 데이터를 모두 Map에 담아 통지한다.
 * 원본 Observable 에서 완료 통지를 받는 즉시 Map을 통지한다.
 * 이미 사용중인 key(키)를 또 생성하면 기존에 있던 key(키)와 value(값)을 덮어쓴다.
 * 통지되는 데이터는 원본데이터를 담은 Map 하나이므로 Single로 반환된다.
 */
public class _7_ObservableToMapExample01 {
    public static void main(String[] args) {
        Single<Map<String, String>> single =
                Observable.just("a-Alpha", "b-Bravo", "c-Charlie", "e-Echo")
                        .toMap(data -> data.split("-")[0]); // 반환값은 Map의 key가 된다.

        single.subscribe(map -> Logger.log(LogType.ON_NEXT, map));
    }
}
