package com.designpattern.report._17_mediator.step1_before;

/**
 * 여기서 모든 객체를 의존하고있다.
 * 코드 변경할때마다 의존성이 있는 클래스의 테스트/변경도 발생한다.
 */
public class CleaningService {
    public void clean(Gym gym) {
        System.out.println("clean " + gym);
    }

    public void getTower(Guest guest, int numberOfTower) {
        System.out.println(numberOfTower + " towers to " + guest);
    }

    public void clean(Restaurant restaurant) {
        System.out.println("clean " + restaurant);
    }
}
