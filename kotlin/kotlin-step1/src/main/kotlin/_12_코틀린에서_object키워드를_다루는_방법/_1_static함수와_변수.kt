package _12_코틀린에서_object키워드를_다루는_방법

fun main() {
    // JAVA에서 이름 없는 동반객체
//    Person.Companion.newBaby("ABC")
    // JAVA에서 이름 있는 동반객체
//    Person.Factory.newBaby("ABC")
}

class Person private constructor(
    var name: String,
    var age: Int
) {
    // static : 클래스가 인스턴스화 될때 새로운 값이 복제되는것이 아니라 정저긍로 인스턴스끼리의 값을 공유한다.
    // companion objedct : 클래스와 동행하는 유일한 오브젝트 (동반객체도 하나의 객체로 간주된다. 이름을 붙일 수 있고, interface를 구현할수도 있다.)
    // kotlin 에는 static이 없다. companion object 라고 해야한다.
    companion object Factory : Log { // 해당 블록 안에는 모두 static 으로 선언된다.
        // const : 컴파일시에 변수가 할당된다. (상수에 붙이기 위한 용도, 기본 타입과 String에 붙일 수 있음)
        // 기본 val : 런타임시에 변수가 할당된다.
        const val MIN_AGE = 1

        fun newBaby(name: String): Person {
            return Person(name, MIN_AGE)
        }

        override fun log() {
            println("나는 Person 클래스의 동행 객체입니다.")
        }

        // 유틸성 함수들을 넣어도 되지만, 최상단 파일을 활용하는 것을 추천한다.
    }
}