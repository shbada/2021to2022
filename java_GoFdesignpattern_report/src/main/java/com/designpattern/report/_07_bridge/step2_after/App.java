package com.designpattern.report._07_bridge.step2_after;

import com.designpattern.report._07_bridge.step2_after.abstraction.Champion;
import com.designpattern.report._07_bridge.step2_after.concrete.KDA;
import com.designpattern.report._07_bridge.step2_after.concrete.PoolParty;
import com.designpattern.report._07_bridge.step2_after.refinedAbstraction.아리;

/**
 * 브릿지 패턴
 * 추상적인것과 구체적인것을 분리하여 연결하는 패턴
 *
 * [장점]
 * 추상적인 코드를 구체적인 코드 변경 없이도 독립적으로 확장할 수 있다.
 * 추상적인 코드와 구체적인 코드를 분리할 수 있다.
 *
 * [단점]
 * 계층 구조가 늘어나 복잡도가 증가할 수 있다.
 */
public abstract class App implements Champion {

    public static void main(String[] args) {
        Champion kda아리 = new 아리(new KDA());
        kda아리.skillQ();
        kda아리.skillW();

        Champion poolParty아리 = new 아리(new PoolParty());
        poolParty아리.skillR();
        poolParty아리.skillW();
    }
}
