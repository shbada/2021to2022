fun main() {
    val question = "메롱"

    val answer = 42 // Type 생략 가능
    val answer2 : Int = 42

    val answer3: Int // 초기화하지 않을시, 타입을 명시해줘야한다.
    answer3 = 42

    // 변경 불가능한 함수
    val a = 1
//    a = 2

    // 변경 가능한 함수
    var b = 2
    b = 3

    // val 참조 자체는 불변일지라도, 그 참조가 가리키는 객체의 내부 값은 변경이 가능하다.
    val languages = arrayListOf("Java")
    languages.add("Kotlin")

    // var 키워드 사용시, 변수의 값은 변경 가능하지만 타입 변경은 불가능하다.
    var answer5 = 42
//    answer5 = "no answer"
}