package me.whiteship.refactoring._05_global_data._17_encapsulate_variable;

public class Thermostats {

    // 보통 상수값에 global public static 을 사용한다. (초기 셋팅 후, 값을 가져오는것만 사용하는 경우)
    public static Integer targetTemperature = 70; // 타겟 온도

    public static Boolean heating = true; // 모드 1

    public static Boolean cooling = false; // 모드 2

    public static Boolean readInFahrenheit = true; // 온도 종류 (화씨/섭씨)

}
