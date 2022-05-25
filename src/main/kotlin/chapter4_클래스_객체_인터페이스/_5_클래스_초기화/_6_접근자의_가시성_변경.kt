package chapter4_클래스_객체_인터페이스._5_클래스_초기화

/*
접근자의 가시성
get, set 앞에 가시성 변경자를 추가해서 접근자의 가시성을 변경할 수 있다.
접근자의 가시성을 변경하는 방법을 살펴보자.
 */
// 비공개 세터가 있는 프로퍼티 선언하기
class LengthCounter {
    var counter: Int = 0
        private set // 이 클래스 밖에서 이 프로퍼티의 값을 바꿀 수 있다.
    fun addWord(word: String) {
        counter += word.length // 자신에게 추가된 모든 단어의 길이를 합산한다. (default: public)

        // 외부코드에서 단어 길이의 합을 마음대로 바꾸지 못하게 이 클래스 내부에서만 길이를 변경하게 만들고싶다.
        // 그래서 기본 가시성을 가진 게터를 컴파일러가 생성하게 내버려두는 대신 세터의 가시성을 private으로 지정한다.
    }
}

fun main() {
    val lengthCounter = LengthCounter()
    lengthCounter.addWord("Hi!")
    println(lengthCounter.counter) // 3
}