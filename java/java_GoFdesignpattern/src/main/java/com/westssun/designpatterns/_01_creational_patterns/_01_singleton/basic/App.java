package com.westssun.designpatterns._01_creational_patterns._01_singleton.basic;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class App {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // Settings1 settings1 = new Settings1();
        // Settings1 settings2 = new Settings1();

        // true
        //System.out.println(settings1 != settings2);

        /** **** */

        // private 생성자 전환 후
        Settings1 settings1 = Settings1.getInstance();
        Settings1 settings2 = Settings1.getInstance();

        // true
        // System.out.println(settings1 != settings2);

        // false (싱글톤 패톤 적용 후)
        System.out.println(settings1 != settings2);
        // 얘를 어떻게 true 로 할까?

        /** 싱글톤을 깨뜨리는 방법 */
        /* 1) 리플렉션 사용 */
        Constructor<Settings1> declaredConstructor = Settings1.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true); // private 접근 가능
        Settings1 settings3 = declaredConstructor.newInstance();

        System.out.println(settings1 != settings3);

        /* 2) 직렬화, 역직렬화 사용 */

    }
}
