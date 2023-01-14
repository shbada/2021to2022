package com.designpattern.report._21_strategy.step2_after;

import com.designpattern.report._21_strategy.step2_after.strategy.Speed;

public class BlueLightRedLight {

    // 호출 시점에 파라미터로 전략을 넘긴다.
    public void blueLight(Speed speed) {
        speed.blueLight(); // 작업 위임 (이 파일이 변경되도 전략에는 영향이 없다)
    }

    public void redLight(Speed speed) {
        speed.redLight();
    }
}
