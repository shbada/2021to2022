package chapter5_람다로_프로그래밍._5_수신객체_지정람다

/*
수신 객체 지정 람다 : 수신 객체를 명시하지 않고 람다의 본문 안에서 다른 객체의 메서드를 호출할 수 있게 한다.
 */

/*
with 라이브러리 함수를 사용하여 어떤 객체의 이름을 반복하지 않고도 그 객체에 대해 다양한 연산을 수행할 수 있다.
 */

/** 매번 result 반복 사용 */
fun alphabet1() : String {
    val result = StringBuilder()

    for (letter in 'A'..'Z') {
        result.append(letter)
    }

    result.append("\nNow I know the alphabet!")
    return result.toString()
}

fun main() {
    println(alphabet1())
}

/*
with 함수는 첫번째 인자로 받은 객체를 두번째 인자로 받은 람다의 수신 객체로 만든다.
인자로 받은 람다 본문에서는 this를 사용해 그 수신 객체에 접근할 수 있다.
 */
fun alphabet2() : String {
    val stringBuilder = StringBuilder()

    /* 파라미터는 2개다. 첫번째는 StringBuilder, 두번째 람다 */
    return with(stringBuilder) { /* 메서드를 호출하려는 수신 객체를 지정한다. */
        for (letter in 'A'..'Z') {
            this.append(letter)  /* this를 명시해서 앞에서 지정한 수신 객체의 메서드를 호출한다. */
        }

        append("\nNow I know the alphabet!")  /* "this"를 생략하고 메서드를 호출한다. */
        this.toString() /* 람다에서 값을 반환한다. */
    }
}

/* with와 식을 본문으로 하는 함수를 활용해 알파벳 만들기 */
/* StringBuilder의 인스턴스를 만들고 즉시 with에게 인자로 넘기고, 람다 안에서 this를 사용하여 그 인스턴스를 참조한다.
* with가 반환하는 값은 람다 코드를 실행한 겨로가다. 그 결과는 람다 식의 본문에 있는 마지막 식의 값이다.
* */
fun alphabet3() = with(StringBuilder()) {
    for (letter in 'A'..'Z') {
        append(letter)
    }

    append("\nNow I know the alphabet!")
    toString() // 람다 값 반환
}