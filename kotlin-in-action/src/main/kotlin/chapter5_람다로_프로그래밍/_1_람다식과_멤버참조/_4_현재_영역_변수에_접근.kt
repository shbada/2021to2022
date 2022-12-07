package chapter5_람다로_프로그래밍._1_람다식과_멤버참조

/*
자바 메서드 안에서 무명 내부 클래스를 정의할때 그 메서드의 로컬 변수를 무명 내부 클래스에서 사용할 수 있다.
람다 안에서도 같은 일을 할 수 있다.

람다 안에서도 동일하게 적용할 수 있다.
람다를 함수 안에서 정의하면 함수의 파라미터뿐 아니라 람다 정의의 앞에 선언된 로컬 변수까지 람다에서 모두 사용할 수 있다.
 */

// forEach
fun printMessagesWithPrefix(messages: Collection<String>, prefix: String) {
    messages.forEach {
        println("$prefix $it") // 람다 내부에서 함수의 "prefix" 변수 사용
    }
}

fun main() {
    val errors = listOf("403 Forbidden", "404 Not Found")
    printMessagesWithPrefix(errors, "Error:")

    printProblemCounts(listOf("200 OK", "418 I'm a teapot", "500 Internal Server Error"))
}

// 코틀린에서는 final 변수가 아닌 변수에도 접근할 수 있다.
// 람다 안에서 바깥의 변수를 변경할 수 있다.
fun printProblemCounts(response: Collection<String>) {
    var clientErrors = 0 // 람다에서 사용할 변수를 정의한다.
    var serverErrors = 0 // 람다에서 사용할 변수를 정의한다.

    /* 람다 안에서 사용하는 변수를 람다가 포획한 변수 (capture)라고 부른다 */
    response.forEach {
        if (it.startsWith("4")) {
            clientErrors++ // 람다 안에서 외부의 로컬 변수 값 변경한다.
        } else if (it.startsWith("5")) {
            serverErrors++ // 람다 안에서 외부의 로컬 변수 값 변경한다.
        }
    }

    println("$clientErrors client errors, $serverErrors server errors")
}

/*
어떤 함수가 자신의 로컬 변수를 포획한 람다를 반환하거나 다른 변수에 저장한다면,
로컬 변수의 생명주기와 함수의 생명주기가 달라질 수 있다.
포획한 변수가 있는 람다를 저장해서 함수가 끝난 뒤에 실행해도 람다의 본문 코드는 여전히 포획한 변수를 읽거나 쓸 수 있다.

파이널 변수를 포획한 경우에는 람다 코드를 변수값과 함께 저장한다.
파이널이 아닌 변수를 포획한 경우에는 변수를 특별한 래퍼로 감싸서 나중에 변경하거나 읽을 수 있게한 다음,
래퍼에 대한 참조를 람다 코드와 함께 저장한다.
 */

// 람다를 이벤트 핸들러나 다른 비동기적으로 실행되는 코드로 활용하는 경우 함수 호출이 끝난 다음에 로컬 변수가 변경될 수도 있다.
//fun tryToCountButtonClicks(button: Button) : Int {
//    var clicks = 0;
//    button.onclick { clicks++ }
//    return clicks
//}

/*
이 함수는 항상 0을 반환한다.
onlick 핸들러는 호출될때마다 clicks의 값을 증가시키지만 그 값의 변경을 관찰할 수는 없다.
핸들러는 tryToCountButtonClicks가 clicks를 반환한 다음에 호출되기 때문이다.

이 함수를 제대로 구현하려면 클릭 횟수를 세는 카운터 변수를 함수의 내부가 아니라 클래스의 프로퍼티나 전역 프로퍼티 등의 위치로
빼내서 나중에 변수 변화를 살펴볼 수 있게 해야한다.
 */
