package com.itvillage.chapter05.chapter0506;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import io.reactivex.Observable;

/*
RxJava 는 이 코드처럼 try~catch 로 에러를 해결할 수 없다.
-> The exception was not handled due to missing onError handler
 */
public class _1_CanNotUseTryCatchExample {
    public static void main(String[] args) {
        try {
            Observable.just(2)
                    .map(num -> num / 0)
                    .subscribe(System.out::println);
        } catch (Exception e) {
            Logger.log(LogType.PRINT, "# 에러 처리가 필요: " + e.getCause());
        }
    }
}
