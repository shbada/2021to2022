package chapter4_클래스_객체_인터페이스._2_변경자._가시성_변경자

internal open class TalkativeButton : Focusable {
    private fun yell() = println("Hey!")
    protected fun whisper() = println("Let's talk!")
}

/*
코틀린은 public 함수인 giveSpeech 안에서 그보다 가시성이 더 낮은 타입인 TalkativeButton을 참조 못하게한다.
어떤 클래스의 기반 타입 목록에 들어있는 타입이나 제네릭 클래스의 타입 파라미터에 들어있는 타입의 가시성은
그 클래스 자신의 가시성과 같거나 높아야한다.
메서드의 시그니처에 사용된 모든 타입의 가시성은 그 메서드의 가시성과 같거나 더 높아야 한다.
이런 규칙은 어떤 함수를 호출하거나 어떤 클래스를 확장할때 필요한 모든 타입에 접근할 수 있게 보장해준다.

여기서 컴파일 오류를 없애려면, giveSpeech 확장 함수의 가시성을 internal 로 바꾸거나,
TalktiveButton 클래스의 가시성을 public 으로 바꿔야한다.

자바에서는 같은 패키지 안에서 protected 멤버에 접근할 수 있지만, 코틀린에서는 그렇지 않다.
자바와 코틀린의 protected가 다르다는 사실에 유의하라.
코틀린의 가시성 규칙은 단순하다.
protected 멤버는 오직 어떤 클래스나 그 클래스를 상속한 클래스 안에서만 보인다.
클래스를 확장한 함수는 그 클래스의 private이나 protected 멤버에 접근할 수 없다.

코틀린과 자바 가시성 규칙의 또 다른 차이는 코틀린에서는 외부 클래스가 내부 클래스나
중첩된 클래스의 private 멤버에 접근할 수 없다는 점이다.
 */
//fun TalkativeButton.giveSpeech() { // 오류 : "public" 멤버가 자신의 "internal" 수신 타입인 "TalkativeButton"를 노출함
//    yell() // yell 에 접근할 수 없음 : yell 은 TalkativeButton의 private 멤버다.
//    whisper() // whisper 에 접근할 수 없음 : whisper 은 TalkativeButton 의 protected 멤버다.
//}
internal fun TalkativeButton.giveSpeech() { // 오류 : "public" 멤버가 자신의 "internal" 수신 타입인 "TalkativeButton"를 노출함
//    yell() // yell 에 접근할 수 없음 : yell 은 TalkativeButton의 private 멤버다.
//    whisper() // whisper 에 접근할 수 없음 : whisper 은 TalkativeButton 의 protected 멤버다.
}