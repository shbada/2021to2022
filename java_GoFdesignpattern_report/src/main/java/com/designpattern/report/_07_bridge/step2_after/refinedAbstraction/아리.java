package com.designpattern.report._07_bridge.step2_after.refinedAbstraction;

import com.designpattern.report._07_bridge.step2_after.abstraction.Skin;

/**
 * Skin 과 상관없이 챔피언을 늘릴 수 있다.
 *
 * Refined Abstraction
 */
public class 아리 extends DefaultChampion {

    public 아리(Skin skin) {
        super(skin, "아리");
    }
}
