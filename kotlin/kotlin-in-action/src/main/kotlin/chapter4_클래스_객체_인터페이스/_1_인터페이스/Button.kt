package chapter4_클래스_객체_인터페이스._1_인터페이스

// 클래스 이름 뒤에 콜론(:)을 붙이고 인터페이스와 클래스 이름을 적는 것으로 클래스 확장과 인터페이스 구현을 모두 처리한다.
// 자바와 마찬가지로 클래스는 인터페이스를 원하는 만큼 개수 제한 없이 마음대로 구현할 수 있다.
// 클래스는 오직 하나만 확장할 수 있다.
class Button : Clickable {
    // override 변경자는 상위 클래스나 상위 인터페이스에 있는 프로퍼티나 메서드를 오버라이드한다는 표시다.
    // 자바와 달리 코틀린은 override 변경자를 꼭 사용해야한다.
    override fun click() {
        println("I was clicked")
    }
}

fun main() {
    Button().click(); // I was clicked
}