package chapter3_함수_정의와_호출._5_확장함수_확장프로퍼티

/*
확장 함수는 오버라이드 할 수 없다.
코틀린의 메서드 오버라이드도 일반적인 객체지향의 메서드 오버라이드와 마찬가지다.
하지만 확장 함수는 오버라이드 할 수 없다.
 */

open class View {
    open fun click() = println("View Checked")
}

/*
Button은 View의 하위 타입이 된다.
View 타입 변수를 선언해도 Button 타입 변수를 그 변수애 대입할 수 있다.
 */
class Button: View() { // Button 이 View 를 확장한다.
    override fun click() {
        println("Button Checked")
    }
}

/*
확장 함수는 클래스의 일부가 아니다.
확장 함수는 클래스 밖에 선언된다.
이름과 파라미터가 완전히 같은 확장 함수를 기반 클래스와 하위 클래스에 대해 정의해도
실제로는 확장 함수를 호출할때 수신 객체로 지정한 변수의 정적 타입에 의해 어떤 확장 함수가 호ㅜㄹ될지 결정된다.
그 변수에 저장된 객체의 동적인 타입에 의해 확장 함수가 결정되지 않는다.
 */
fun main() {
    val view: View = Button()
    view.click() // view 에 저장된 실제 타입에 따라 호출할 메서드가 결정된다. (Button.click())

    // 확장함수 호출
    val view2: View = Button()
    /*
        확장 함수는 정적으로 결정된다. View.showOff()
        view2 가 가리키는 객체의 실제 타입이 Button 이지만, 이 경우 view2이 타입이 View이기 때문에 View의 확장함수가 호출된다.
        확장 함수는 첫번째 인자가 수신 객체인 정적 자바 메서드로 컴파일한다.
        코틀린은 확장 함수를 정적으로 결정하므로 오버라이드 할 수 없다.
     */
    view2.showOff() // view!
}

// 확장함수 선언
fun View.showOff() = println("view!")
fun Button.showOff() = println("button!")

