package com.itvillage.chapter05.chapter0501;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;

public class _5_ObservableFromIterableExample {
    public static void main(String[] args){
        List<String> countries = Arrays.asList("Korea", "Canada", "USA", "Italy");

        // 데이터를 순서대로 통지하고 구독함
        Observable.fromIterable(countries)
                .subscribe(country -> Logger.log(LogType.ON_NEXT, country));
    }
}
