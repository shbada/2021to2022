package com.westssun.designpatterns._01_singleton;

public class App {
    public static void main(String[] args) {
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
    }
}
