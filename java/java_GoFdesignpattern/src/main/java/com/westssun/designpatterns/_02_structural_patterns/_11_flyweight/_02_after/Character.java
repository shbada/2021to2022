package com.westssun.designpatterns._02_structural_patterns._11_flyweight._02_after;

/**
 * 자주 변하는 속성
 */
public class Character {

    private char value;

    private String color;

    private Font font;

    public Character(char value, String color, Font font) {
        this.value = value;
        this.color = color;
        this.font = font;
    }
}
