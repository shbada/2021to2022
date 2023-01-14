package me.whiteship.refactoring._04_long_parameter_list;

/**
 * 냄새 4. 긴 매개변수 목록 (Long Parameter List)
 *
 * - 어떤 함수에 매개변수가 많을수록 함수의 역할을 이해하기 어려워진다.
 *  > 과연 그 함수는 한가지 일을 하고있는게 맞나?
 *  > 불필요한 매개변수는 없는가?
 *  > 하나의 레코드로 뭉칠 수 있는 매개변수 목록은 없는가?
 * - 어떤 매개변수를 다른 매개변수를 통해 알아낼 수 있다면, "매개변수를 질의 함수로 바꾸기(Replace Parameter With Query)"
 * 를 사용할 수 있다.
 * - 기존 자료구조에서 세부적인 데이터를 가져와서 여러 매개변수를 넘기는 대신 "객체 통째로 넘기기(Preserve Whole Object)"
 * 를 사용할 수 있다.
 * - 일부 매개변수들이 대부분 같이 넘겨진다면, "매개변수 객체 만들기(Introduce Parameter Object)"를 적용할 수 있다.
 * - 매개변수가 플래그로 사용된다면, "플래그 인수 제거하기(Remove Flag Argument)"를 사용할 수 있다.
 * - 여러 함수가 일부 매개변수를 공통적으로 사용한다면 "여러 함수를 클래스로 묶기(Combine Functions into Class)"를 통해
 * 매개변수를 해당 클래스의 필드로 만들고 메서드에 전달해야 할 매개변수 목록을 줄일 수 있다.
 */
public class Main {
}
