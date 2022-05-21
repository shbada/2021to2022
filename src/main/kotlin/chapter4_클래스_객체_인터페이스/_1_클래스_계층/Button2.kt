package chapter4_클래스_객체_인터페이스._1_클래스_계층

class Button2 : Clickable, Focusable {
    override fun click() {
        println("I was clicked")
    }

    // showOff()를 구현하지 않으면 컴파일 에러
    /*
    Class 'Button2' must override public open fun showOff():
    Unit defined in chapter4_클래스_객체_인터페이스._1_클래스_계층.Clickable because it inherits multiple interface methods of it
     */

//    override fun showOff() {
//        println("Button2 showOff")
//    }

    /*
    이름과 시그니처가 같은 멤버 메서드에 대해 2개 이상의 디폴트 구현이 있는 경우,
    인터페이스를 구현하는 하위 클래스에서 명시적으로 새로운 구현을 제공해야한다.
     */
    override fun showOff() {
        // 상위 타입의 이름을 꺽쇠 괄호(<>) 사이에 넣어서 "super"를 지정하면
        // 어떤 상위 타입의 멤버 메서드를 호출할지 지정할 수 있다.
        // JAVA : Clickable.super.showOff()
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }
}

fun main() {
    Button2().click(); // I was clicked
    Button2().showOff();

    val button2 = Button2()
    button2.showOff() // I'm clicked   I'm focusable!
    // 자동 상속
    button2.setFocus(true) // I got focus.
    button2.click() // I was clicked
}
