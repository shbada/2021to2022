package me.whiteship.refactoring._11_primitive_obsession._30_repliace_primitive_with_object;

/**
 * 리팩토링 30. 기본형을 객체로 바꾸기 (Replace Primitive With Object)
 * - 개발 초기에는 기본형으로 표현한 데이터가 나중에는 해당 데이터와 관련있는 다양한 기능을 필요로하는 경우가 발생한다.
 * - 기본형을 사용한 데이터를 감싸줄 클래스를 만들면, 필요한 기능을 추가할 수 있다.
 */
public class Order {

    private String priority;

    public Order(String priority) {
        this.priority = priority;
    }

    public String getPriority() {
        return priority;
    }
}
