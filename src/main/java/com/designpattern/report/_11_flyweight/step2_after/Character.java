package com.designpattern.report._11_flyweight.step2_after;

import com.designpattern.report._11_flyweight.step2_after.flyweight.Font;

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
