package me.whiteship.refactoring._05_global_data._17_encapsulate_variable.done;

public class Thermostats_Done {
    // private 캡슐화

    private static Integer targetTemperature = 70; // 타겟 온도

    private static Boolean heating = true; // 모드 1

    private static Boolean cooling = false; // 모드 2

    private static Boolean readInFahrenheit = true; // 온도 종류 (화씨/섭씨)

    /* 1. getter/setter 만든다. */
    public static Integer getTargetTemperature() {
        return targetTemperature;
    }

    public static void setTargetTemperature(Integer targetTemperature) {
        Thermostats_Done.targetTemperature = targetTemperature;
    }

    public static Boolean getHeating() {
        return heating;
    }

    public static void setHeating(Boolean heating) {
        /* validation 추가 가능 */
        Thermostats_Done.heating = heating;

        /* notify 추가 가능 */
    }

    public static Boolean getCooling() {
        return cooling;
    }

    public static void setCooling(Boolean cooling) {
        Thermostats_Done.cooling = cooling;
    }

    public static Boolean getReadInFahrenheit() {
        return readInFahrenheit;
    }

    public static void setReadInFahrenheit(Boolean readInFahrenheit) {
        Thermostats_Done.readInFahrenheit = readInFahrenheit;
    }

    // 필드명 변경이 어려운경우 set** 메서드를 하나 만들면 된다.
}
