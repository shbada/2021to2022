package com.designpattern.report._21_strategy.step1_before;

public class Client {

    public static void main(String[] args) {
        BlueLightRedLight blueLightRedLight = new BlueLightRedLight(3);
        blueLightRedLight.blueLight();
        blueLightRedLight.redLight();
    }
}
