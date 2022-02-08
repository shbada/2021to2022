package com.westssun.designpatterns._01_singleton.enums;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class EnumApp {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        EnumSettings enumSettings = EnumSettings.INSTANCE;

        /**
         * enum 은 리플렉션에 안전한 코드다.
         * 리플렉션을 뚫을 수 없다.
         */
        // com.westssun.designpatterns._01_singleton.enums.EnumSettings.<init>() 에러 발생
        // 기본 생성자를 못가져왔다.
        Constructor<EnumSettings> constructor = EnumSettings.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        EnumSettings enumSettings1 = constructor.newInstance();

        // Exception in thread "main" java.lang.IllegalArgumentException: Cannot reflectively create enum objects
        // 이 방법도 에러가 발생한다.
        EnumSettings enumSettings2 = null;
        Constructor<?>[] declaredConstructors = EnumSettings.class.getDeclaredConstructors();

        for (Constructor<?> constructor1 : declaredConstructors) {
            constructor1.setAccessible(true);
            enumSettings2 = (EnumSettings) constructor1.newInstance("INSTANCE");
        }


        System.out.println(enumSettings == enumSettings1);
    }
}
