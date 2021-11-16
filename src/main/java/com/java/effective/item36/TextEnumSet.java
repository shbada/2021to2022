package com.java.effective.item36;

import java.util.EnumSet;
import java.util.Set;

public class TextEnumSet {
    public enum Style {
        BOLD,
        ITALIC,
        UNDERLINE,
        STRIKETHROUGH
    }

    // 어떤 Set을 넘겨도 되나, EnumSet 이 가장 좋다.
    public void applyStyles(Set<Style> styles) {

    }

    public static void main(String[] args) {
        TextEnumSet textEnumSet = new TextEnumSet();
        textEnumSet.applyStyles(EnumSet.of(Style.BOLD, Style.ITALIC));
    }
}
