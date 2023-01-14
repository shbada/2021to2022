package com.westssun.designpatterns._02_structural_patterns._07_bridge._01_before;

import com.westssun.designpatterns._02_structural_patterns._07_bridge._02_after.Skin;

/**
 * Client : Abstraction 을 사용
 * Abstraction : 추상적인 로직을 갖고있는 클래스
 * Refined Abstraction : 구현
 *
 * Implementation : 구체적인 정보, 상태, 액션, 코드 등을 담고있는 부분
 * (인터페이스-구현클래스 구조)
 */
public interface Champion extends Skin {

    void move();

    void skillQ();

    void skillW();

    void skillE();

    void skillR();

}
