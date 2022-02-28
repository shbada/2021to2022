package com.designpattern.report._02_factory_method.step2_after;

/**
 * 팩토리 메서드 패턴
 *
 * 1) 장점
 * 기존 코드를 건드리지 않고, 새로운 인스턴스(그와 같은) 를 다른 방법으로 얼마든지 확장이 가능하다.
 * 결합도를 느슨하게 (느슨한 결합)
 *
 * - 대신 클래스가 늘어나는 단점이 있음 (WhiteShipFactory, BlackShipFactory)
 *
 * 2) 확장에 열려있고 변경에 닫혀있는 객체 지향 원칙
 * 확장이 열려있다, 변경이 닫혀있다 : 기존 코드를 변경하지 않으면서 새로운 기능을 얼마든지 확장할 수 있는것
 *
 * 3) 자바 8에 추가된 default 메서드
 * 인터페이스의 기본적인 구현체를 만들 수 있게되었다.
 * 추상메서드만 만들수 있었을때, 새로 추가하면 구현하고있던 하위 클래스들에 오류/수정이 발생한다.
 *
 * 4) 자바 9에 인터페이스 private 메서드가 출연함
 */
public interface ShipFactory {
    // orderShip
    default Ship orderShip(String name, String email) {
        // 어떤일이 일어나는지 default 로 정의해보자.
        validate(name, email);

        prepareFor(name);

        // 하위클래스에 위임
        Ship ship = createShip();

        // notify
        sendEmailTo(email, ship);

        return ship;
    }

    /**
     * 추상메서드
     * 하위클래스가 정의해야하는 메서드
     * @return
     */
    Ship createShip();

    // java9 부터 인터페이스에 private 메서드 사용 가능
    private void validate(String name, String email) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("배 이름을 지어주세요.");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("연락처를 남겨주세요.");
        }
    }

    private void prepareFor(String name) {
        System.out.println(name + " 만들 준비 중");
    }

    private void sendEmailTo(String email, Ship ship) {
        System.out.println(ship.getName() + " 다 만들었습니다.");
    }
}
