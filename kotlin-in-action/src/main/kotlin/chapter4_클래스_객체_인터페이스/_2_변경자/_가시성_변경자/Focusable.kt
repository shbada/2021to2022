package chapter4_클래스_객체_인터페이스._2_변경자._가시성_변경자

interface Focusable {
    fun setFocus(b: Boolean) = println("I ${if (b) "got" else "lost"} focus.")
    fun showOff() = println("I'm focusable!")
}