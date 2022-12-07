package chapter3_함수_정의와_호출._5_컬렉션_처리

/*
중위 호출
중외호출 시에는 수신 객체와 유일한 메서드 인자 사이에 메서드 이름을 넣는다.

일반 방식 호출 :  1.to("one")
중위 호출 : 1 to "one"
(객체, 메서드 이름, 유일한 인자 사이에 공백이 들어가야한다.)

인자가 하나뿐인 일반 메서드나 인자가 하나뿐인 확장함수에 중위 호출을 사용할 수 있다.
함수를 중위 호출에 사용하게 허용하고 싶으면 infix 변경자를 함수 선언 앞에 추가해야한다.

public infix fun <A, B> A.to(that: B): Pair<A, B> = Pair(this, that)

구조 분해 선언
// Pair 의 내용으로 두 변수를 즉시 초기화할 수 있다.
val (number, name) = 1 to "one"

 */
fun main() {
    // to : 중위 호출(infix call)이라는 특별한 방식으로 to 라는 일반 메서드를 호출한다.
    val map = mapOf(1 to "one", 7 to "seven", 53 to "fifty-three")

    // 구조 분해 선언
    val (number, name) = 1 to "one"
}