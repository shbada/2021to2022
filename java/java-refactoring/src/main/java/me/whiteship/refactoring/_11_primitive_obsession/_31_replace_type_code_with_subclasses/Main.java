package me.whiteship.refactoring._11_primitive_obsession._31_replace_type_code_with_subclasses;

/**
 * 리팩토링 31. 타입 코드를 서브 클래스로 바꾸기
 * (Replace Type Code With Subclasses)
 *
 * - 비슷하지만 다른 것들을 표현해야 하는 경우, 문자열, 열거형(enum), 숫자 등으로 표현하기도 한다.
 * 예: 주문타입 : 일반주문, 빠른주문
 *
 * - 타입을 서브클래스로 바꾸는 계기
 * > 조건문을 다형성으로 표현할 수 있을때, 서브 클래스를 만들고 "조건부 로직을 다형성으로 바꾸기"를 적용한다.
 * > 특정 타입에만 유효한 필드가 있을때, 서브 클래스로 만들고 "필드 내리기"를 활용한다.
 */
public class Main {
}
