package com.designpattern.report._07_bridge.step2_after.concrete;

import com.designpattern.report._07_bridge.step2_after.abstraction.Skin;

/**
 * 스킨 생성
 *
 * ConcreteImplementation
 */
public class KDA implements Skin {
    @Override
    public String getName() {
        return "KDA";
    }
}
