package _3_코틀린에서_Type을_다루는_방법

fun main() {
    /*
    Any
    : Java의 Object 역할 (모든 객체의 초ㅓㅣ상위 타입)
    : 모든 Primitive Type의 최상위 타입도 Any이다.
    : Any 자체로는 null 을 포함할 수 없다. null로 표현하려면 Any?로 표현
    : Any에 equals, hashCode, toString도 존재한다.
     */

    /*
    Unit
    : Java의 void와 동일한 역할
    : void와 다르게 Unit은 그 자체로 타입 인자로 사용 가능하다.
    : 함수형 프로그래밍에서 Unit은 단 하나의 인스턴스만 갖는 타입을 의미한다.
      즉, 코틀린의 Unit은 실제 존재하는 타입이라는 것을 표현한다.
     */

    /*
    Nothing
    : 함수가 정상적으로 끝나지 않았따는 사실을 표현하는 역할이다.
    : 무조건 예외를 반환하는 함수나 무한루프 함수 등에 사용한다.
     */
}