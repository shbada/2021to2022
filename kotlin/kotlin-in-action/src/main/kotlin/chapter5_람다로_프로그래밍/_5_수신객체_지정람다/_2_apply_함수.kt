package chapter5_람다로_프로그래밍._5_수신객체_지정람다

/*
apply는 항상 자신에게 전달된 객체(수신 객체)를 반환한다.
 */

/*
apply는 확장 함수로 정의되어있다.
apply의 수신 객체가 전달받은 람다의 수신 객체가 된다.
이 함수에서 apply를 실행한 결과는 StringBuilder 객체다. 그 객체의 toString을 호출해서 String 객체를 얻는다.

이런 apply 함수는 객체의 인스턴스를 만들면서 즉시 프로퍼티 중 일부를 초기화해야하는 경우 유용하다.
자바에서는 보통 별도의 Builder 객체가 이런 역할을 담당한다.
 */
fun alphabet() = StringBuilder().apply {
    for (letter in 'A'..'Z'){
        append(letter)
    }

    append("\nNow I know the alphabet!")
}.toString()

fun main(args: Array<String>) {
    println(alphabet())
}

/* apply를 TextView 초기화에 사용하기 */
/*
TextView 인스턴스를 만들고 즉시 그 인스턴스를 apply에 넘긴다.
apply에 전달된 람다 안에서는 TextView가 수신객체가 된다.
원하는대로 TextView의 메서드를 호출하거나 프로퍼티를 설정할 수 있다.
람다를 실행하고 나면 apply는 람다에 의해 초기화된 TextView 인스턴스를 반환한다.
그 인스턴스가 createViewWithCustomAttributes()의 결과가 된다.
 */
//fun createViewWithCustomAttributes(context: Context) =
//    TextView(context).apply {
//        text = "Sample Text"
//        textSize = 20.0
//        setPadding(10, 0, 0, 0)
//    }

/*
표준 라이브러리 buildString 함수를 사용하면 더 단순화할 수 있다.
buildString은 StringBuilder 객체를 만드는 일과 toString 호출해주는 일을 알아서 해준다.
수신 객체 지정 람다 : buildString의 인자
수신 객체 : 항상 StringBuilder이다.
 */
fun alphabet5() = buildString {
    for (letter in 'A'..'Z') {
        append(letter)
    }

    append("\nNow I know the alphabet!")
}