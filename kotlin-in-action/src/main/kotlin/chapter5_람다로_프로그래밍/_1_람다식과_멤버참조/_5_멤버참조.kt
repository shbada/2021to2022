package chapter5_람다로_프로그래밍._1_람다식과_멤버참조

/*
람다를 사용해 코드 블록을 다른 함수에게 인자로 넘길 수 있다.
넘기려는 코드가 이미 함수로 선언된 경우에는 어떻게 해야할까?
그 함수를 호출하는 람다를 만들면 되지만, 이는 중복이다.
 */
data class Person3(val name: String, val age: Int)

fun salute() = println("Salute!")

fun main() {
//    people.maxBy(Person::age) // 멤버 참조 뒤에는 괄호를 넣으면 안된다.
//    people.maxBy { p -> p.age }
//    people.maxBy { it.age }

    // 클래스 이름을 생략하고 ::로 참조를 한다. 멤버 참조를 run 라이브러리 함수에 넘긴다.
    // run은 인자로 받은 람다를 호출한다.
    run(::salute) // 최상위 함수 참조

    // sendEmail 함수에게 작업을 위임한다.
//    val action = { person: Person, message: String -> sendEmail(person, message) }
    // 람다 대신 멤버 참조를 쓸수 있다.
//    val nextAction = ::sendEmail

    // :: 뒤에 클래스 이름을 넣으면 생성자 참조를 만들 수 있다.
    val createPerson = ::Person3 // 생성자 참조 (Person의 인스턴스를 만드는 동작을 값으로 저장한다.)
    val p = createPerson("Alice", 29) // 생성자 참조를 이용해 인스턴스 생성

    // Person3 클래스의 확장함수도 멤버 참조 구문으로 참조를 얻을 수 있다.
    var predicate = Person3::isAdult
}

fun Person3.isAdult() = age >= 21