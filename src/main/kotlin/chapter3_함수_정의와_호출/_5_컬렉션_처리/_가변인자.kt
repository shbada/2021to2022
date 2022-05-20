package chapter3_함수_정의와_호출._5_컬렉션_처리

/*
리스트를 생성하는 함수를 호출할때 원하는 만큼 많이 원소를 전달할 수 있다.

가변 길이 인자 (vararg)
가변 길이 인자는 메서드를 호출할때 원하는 개수만큼 값을 인자로 넘기면 자바 컴파일러가 배열에 그 값들을 넣어주는 기능이다.
코틀린의 가변 길이 인자도 자바와 비슷하다.
타입 뒤에 ...를 붙이는 대신, 코틀린에서는 파라미터 앞에 vararg 변경자를 붙인다.

코틀린은 배열을 명시적으로 풀어서 배열의 각 원소가 인자로 전달되게 해야한다.
스프레드 (spread) 연산자가 그런 작업을 해준다. (배열 앞에 *를 붙인다.)
 */

fun main(args: Array<String>) {
    // public fun <T> listOf(vararg elements: T): List<T> = if (elements.size > 0) elements.asList() else emptyList()
    val list = listOf(2, 3, 5, 7, 11)

    // spread 연산자가 배열의 내용을 펼쳐준다.
    // 스프레드 연산자를 통하면 배열에 들어있는 값과 다른 여러 값을 함께 써서 함수를 호출할 수 있다.
    val list2 = listOf("args : ", *args)
    println(list)
}