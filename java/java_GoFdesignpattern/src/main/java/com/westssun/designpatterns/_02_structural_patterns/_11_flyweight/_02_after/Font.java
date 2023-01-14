package com.westssun.designpatterns._02_structural_patterns._11_flyweight._02_after;

/**
 * 변하지않는 속성
 *
 * final 변수 선언
 * 상속 불가능
 * set 메서드 제공 X (immutable)
 */
public final class Font {

    final String family;

    final int size;

    public Font(String family, int size) {
        this.family = family;
        this.size = size;
    }

    public String getFamily() {
        return family;
    }

    public int getSize() {
        return size;
    }
}
