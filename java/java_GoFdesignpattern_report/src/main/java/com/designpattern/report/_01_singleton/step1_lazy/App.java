package com.designpattern.report._01_singleton.step1_lazy;

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
        Singleton settings1 = Singleton.getInstance();
        Singleton settings2 = Singleton.getInstance();

        // true
        // System.out.println(settings1 != settings2);

        // false (싱글톤 패톤 적용 후)
        System.out.println(settings1 != settings2);
        // 얘를 어떻게 true 로 할까?

        /** 싱글톤을 깨뜨리는 방법 */
        /* 1) 리플렉션 사용 */
        Constructor<Singleton> declaredConstructor = Singleton.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true); // private 접근 가능
        Singleton settings3 = declaredConstructor.newInstance();

        System.out.println(settings1 != settings3);

        /* 2) 직렬화, 역직렬화 사용 */

    }
}
