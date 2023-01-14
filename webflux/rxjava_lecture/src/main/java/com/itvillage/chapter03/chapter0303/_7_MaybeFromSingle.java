package com.itvillage.chapter03.chapter0303;

import com.itvillage.utils.DateUtil;
import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import io.reactivex.Maybe;
import io.reactivex.Single;

public class _7_MaybeFromSingle {
    public static void main(String[] args){
        Single<String> single = Single.just(DateUtil.getNowDate());

        /* fromSingle : single 객체를 파라미터로 받아서 Maybe 객체를 생성한다. */
        Maybe.fromSingle(single)
                .subscribe(
                        data -> Logger.log(LogType.ON_SUCCESS, "# 현재 날짜시각: " + data),
                        error -> Logger.log(LogType.ON_ERROR, error),
                        () -> Logger.log(LogType.ON_COMPLETE)
                );
    }
}
