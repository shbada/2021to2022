package com.westssun.designpatterns._01_creational_patterns._04_builder._02_after;

import com.westssun.designpatterns._01_creational_patterns._04_builder._01_before.TourPlan;

/**
 * 빌더패턴
 * [장점]
 * - 여러 빌더가 제공하는 메서드의 순서를 강제화할 수 있다. (순차적인 프로세스)
 * - 복잡한 구성을 가진 인스턴스를 만들때 완벽하게 체크를 할 수 있다. (생성자에 모든 로직을 넣는것보다)
 * - 빌더의 확장성
 *  (여러 TourBuilder 가 나올 수도 있다.)
 * - 불안전한 객체를 사용하지 못하게끔 한다. 마지막 getPlan()을 호출해야만 원하는 인스턴스를 받는다.
 *
 * [단점]
 * - 클라이언트에서 TourPlan 을 만들기전에 TourDirector 안의 DefaultTourBuilder 까지 생성해줘야한다.
 *   (TourDirector director = new TourDirector(new DefaultTourBuilder());)
 */
public class App {

    public static void main(String[] args) {
        TourPlanBuilder basic = new DefaultTourBuilder();
        basic.title("칸쿤여행");
        basic.nightsAndDays(2, 3); // 2박3일
        // 이런식으로 할 수도 있지만,
        basic.title("abcde")
                .nightsAndDays(1, 3);
        // 이렇게도 가능하다.

        TourDirector director = new TourDirector(new DefaultTourBuilder());

        /* director 을 통해서 만든다. */
        TourPlan tourPlan = director.cancunTrip();
        TourPlan tourPlan1 = director.longBeachTrip();
    }
}
