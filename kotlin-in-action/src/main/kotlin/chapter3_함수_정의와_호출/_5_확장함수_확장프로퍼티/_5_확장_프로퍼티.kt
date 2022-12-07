package chapter3_함수_정의와_호출._5_확장함수_확장프로퍼티

/*
확장 프로퍼티를 사용하면 기존 클래스 객체에 대한 프로퍼티 형식의 구문으로 사용할 수 있는 API를 추가할 수 있다.
프로퍼티라는 이름으로 불리기는 하지만 상태를 저장할 적절한 방법이 없기 때문에, 실제로 확장 프로퍼티는 아무 상태도 가질 수 없다.
(=기존 클래스의 인스턴스 객체에 필드를 추가할 방법은 없다.)

하지만 프로퍼티 문법으로 더 짧게 코드를 작성할 수 있어서 편한 경우가 있다.
 */

val String.lastChar: Char
    // 최소한 게터는 꼭 정의해야한다.
    get() = get(length - 1)


var StringBuilder.lastChar2: Char
    // 최소한 게터는 꼭 정의해야한다.
    get() = get(length - 1)
    set(value: Char) {
        this.setCharAt(length - 1, value)
    }

fun main() {
    println("Kotlin".lastChar)

    val sb = StringBuilder("Kotlin?")
    sb.lastChar2 = '!'
    println(sb) // Kotlin!

    // JAVA (Get, Set 을 명시적으로 호출)
    // StringUtilkt.getLastChar("Java")
}