package _16_코틀린에서_다양한_함수를_다루는_방법

import _12_코틀린에서_object키워드를_다루는_방법.Person

/*
코틀린은 자바와 호환성 100%를 목표로 한다.
기존 Java 코드 위에 자연스럽게 코틀린 코드를 추가할 수 없을까?
어떤 클래스 안에 있는 메서드 처럼 호출할 수 있지만, 함수는 밖에 만들 수 있게 하자.
함수의 코드 자체는 클래스 밖에 있는데, 마치 클래스 안에 있는 멤버 함수처럼 호출해서 쓰는 방법 - 확장 함수

1) 확장함수가 public이고, 확장함수에서 수신객체클래스의 private 함수를 가져오면 캡슐화가 깨지는거 아닌가?
-> 확장함수는 클래스에 있는 private, protected 멤버를 가져올 수 없다.

2) 멤버함수와 확장함수의 시그니처가 같다면?
-> 멤버함수가 우선적으로 호출된다.

3) 확장함수가 오버라이드 된다면? = Srt -> Train 관계에서 둘다 isExpensive() 를 가지고있을 때
val str1 = Train = Train()
srt1.isExpensive() // Train 의 확장함수

val str1 = Train = Srt()
srt1.isExpensive() // Train 의 확장함수

val str1 = Srt = Srt()
srt1.isExpensive() // Srt 의 확장함수
-> 해당 변수의 현재 타입, 즉, 정적인 타입에 의해 어떤 확장함수가 호출될지 결정된다.

4) JAVA에서 Kotlin의 확장함수를 가져다 사용할 수 있는가?
-> 정적메서드 부르는 것처럼 사용 가능하다.
StringUtilsKt.lastChar("AT") // 파일명 : StringUtils.kt

확장함수라는 개념은 확장 프로퍼티와도 연결된다.
확장 프로퍼티의 원리는 확장함수 + custom getter와 동일하다.
fun String.lastChar(): Char {
    return this[this.length -1]
}
val String.lastChar: Char
    get() = this[this.length - 1]

 */

fun main() {
    val str = "ABC"
    println(str.lastChar())
}

// 확장함수
// String 클래스를 확장한다는 의미
// this 를 통해서 불려진 인스턴스 접근이 가능하다.
// fun 확장하려는 클래스.함수이름(파라미터) : 리턴타입 { // this를 이용해 실제 클래스 안의 값에 접근 }
// this: 수신객체, 확장하려는 클래스 : 수신객체 타입
fun String.lastChar(): Char {
    return this[this.length - 1] // 마지막 문자
}