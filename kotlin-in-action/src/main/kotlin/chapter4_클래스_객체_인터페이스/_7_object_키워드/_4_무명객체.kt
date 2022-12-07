package chapter4_클래스_객체_인터페이스._7_object_키워드

import java.awt.Window
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent

/**
 * 객체 식 : 무명 내부 클래스를 다른 방식으로 작성
 */

/*
object 키워드를 싱글턴과 같은 객체를 정의하고 그 객체에 이름을 붙일 때만 사용하지 않는다.
무명 객체(anonymous object) 를 정의할 때도 object 키워드를 쓴다.
무명 객체는 자바의 무명 내부 클래스를 대신한다.
 */
//window.addMouseListener(
//    // 객체 이름이 없다.
//    // 객체 식은 클래스를 정의하고 그 클래스에 속한 인스턴스를 생성하지만, 그 클래스나 인스턴스에 이름을 붙이지는 않는다.
//    // 이런 경우 보통 함수를 호출하면서 인자로 무명 객체를 넘기기 때문에 클래스와 인스턴스 모두 이름이 필요하지 않다.
//    object : MouseAdapter() { // MouseAdapter를 확장하는 무명 객체를 선언한다.
//        override fun mouseClicked(e: MouseEvent) { // MouseAdapter의 메서드를 오버라인드한다.
//            // ...
//        }
//
//        override fun mouseEntered(e: MouseEvent) {
//            // ...
//        }
//    }
//)

// 객체에 이름을 붙여야 한다면 변수에 무명 객체를 대입하면 된다.
val listener = object : MouseAdapter() { // MouseAdapter를 확장하는 무명 객체를 선언한다.
    override fun mouseClicked(e: MouseEvent) { // MouseAdapter의 메서드를 오버라인드한다.
        // ...
    }

    override fun mouseEntered(e: MouseEvent) {
        // ...
    }
}

/*
자바 : 한 인터페이스만 구현하거나 한 클래스만 확장할 수 있는 자바의 무명 내부 클래스
코틀린 :  여러 인터페이스를 구현하거나 클래스를 확장하면서 인터페이스를 구현할 수 있다.

자바의 무명 클래스와 같이 객체 식 안의 코드는 그 식이 포함된 함수의 변수에 접근할 수 있다.
하지만 자바와 달리 final이 아닌 변수도 객체 식 안에서 사용할 수 있다.
객체 식 안에서 그 변수의 값을 변경할 수 있다.
 */
fun countClicks(window: Window) {
    var clickCount = 0 // 로컬 변수 정의

//    window.addMouseListener(object : MouseAdapter) {
//        override fun mouseClicked(e: MouseEvent) {
//            clickCount++ // 로컬 변수의 값 변경
//        }
//    }
}
