package com.itvillage.chapter05.chapter0502;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import com.itvillage.utils.TimeUtil;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class _11_ObservableSkipExample02 {
    public static void main(String[] args) {
        Observable.interval(300L, TimeUnit.MILLISECONDS) // 0.3초마다 통지
                .skip(1000L, TimeUnit.MILLISECONDS) // 1초까지는 건너뛴다.
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(3000L);
    }
}
