package com.designpattern.report._01_singleton.step2_eager;

import java.lang.reflect.InvocationTargetException;

public class App {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // private 생성자 전환 후
        Singleton settings1 = Singleton.getInstance();
        Singleton settings2 = Singleton.getInstance();

        // true
        // System.out.println(settings1 != settings2);

        // false (싱글톤 패톤 적용 후)
        System.out.println(settings1 != settings2);
    }
}
