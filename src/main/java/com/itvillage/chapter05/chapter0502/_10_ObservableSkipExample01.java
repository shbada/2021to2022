package com.itvillage.chapter05.chapter0502;

import com.itvillage.utils.LogType;
import com.itvillage.utils.Logger;
import io.reactivex.Observable;

public class _10_ObservableSkipExample01 {
    public static void main(String[] args) {
        Observable.range(1, 15)
                .skip(3) // 3개 건너 뛴다. (0, 1, 2가 건너뛴다)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
