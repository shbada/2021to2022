package _1_코틀린에서_변수를_다루는_방법

// var : 변경 가능한 변수
// val : 변경 불가능한 변수

fun main() {
    var number1 = 10L
    val number2 = 10L

    number1 = 5L // 변경 가능
//    number2 = 5L // 변경 불가능

    // 타입 생략 가능
    var number3: Long = 10L

    // 값을 넣어야 타입 추론이 가능하다.
    // 초기화하지 않은 선언은 타입을 넣어줘야한다.
    var number4: Long
    number4 = 1
    print(number4)

    // 불변이지만 초기화되지 않은 변수는 처음에만 값 할당이 가능하다.
    val number5: Long
    number5 = 2
    print(number5)

    // TIP. 모든 변수는 우선 val로 만들고 꼭 필요한 경우 var로 만든다.

    // val 은 불변이지만, 컬렉션은 원소 추가가 가능하다.
}