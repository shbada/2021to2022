package com.westssun.designpatterns._02_structural_patterns._11_flyweight._02_after;

/**
 * 플라이웨이트 패턴
 * - 자주 변하는 속성, 자주 변하지 않는 속성을 분리하고 재사용하여 메모리 사용 줄이기
 * (자주 변하는 속성 : extrinsit), 변하지 않는 속성(intrinsit)
 *
 * [장점]
 * 메모리 사용 줄이기
 *
 * [단점]
 * 코드가 좀더 복잡해짐
 */
public class Client {

    public static void main(String[] args) {
        FontFactory fontFactory = new FontFactory();

        // "Nanum", 12 얘를 Font 로 묶음
        // 변하지 않는 속성 Font 캐시 처리
        Character c1 = new Character('h', "white", fontFactory.getFont("nanum:12"));
        Character c2 = new Character('e', "white", fontFactory.getFont("nanum:12"));
        Character c3 = new Character('l', "white", fontFactory.getFont("nanum:12"));
    }
}
