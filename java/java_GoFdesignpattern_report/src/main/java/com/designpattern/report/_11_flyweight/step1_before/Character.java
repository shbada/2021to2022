package com.designpattern.report._11_flyweight.step1_before;

public class Character {

    private char value;
    private String color;
    private String fontFamily;
    private int fontSize;

    public Character(char value, String color, String fontFamily, int fontSize) {
        this.value = value;
        this.color = color;
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
    }
}
