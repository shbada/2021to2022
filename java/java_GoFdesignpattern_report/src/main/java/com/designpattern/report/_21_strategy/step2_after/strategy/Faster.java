package com.designpattern.report._21_strategy.step2_after.strategy;

public class Faster implements Speed {
    @Override
    public void blueLight() {
        System.out.println("무궁화꽃이");
    }

    @Override
    public void redLight() {
        System.out.println("피었습니다.");
    }
}
