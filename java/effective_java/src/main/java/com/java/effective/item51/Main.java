package com.java.effective.item51;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // List

        // boolean 타입의 경우 '섭씨가 아닌가? 맞는가?'
        boolean isCelsius = false;

        // enum 의 경우에는 다른 값이 추가되더라도 유연학 처리 가능
        System.out.println(TemperatureScaleScale.CELSIUS);
    }

}

enum TemperatureScaleScale {
    FAHRENHEIT,
    CELSIUS
}
