package chapter4_클래스_객체_인터페이스._1_클래스_계층

interface Clickable {
    fun click()

    // 인터페이스 메서드도 디폴트 구현을 제공할 수 있다.
    // 자바에서는 default를 메서드 앞에 붙여야하지만, 코틀린에서는 특별히 쓸 필요가 없다.
    // 그냥 메서드 본문을 메서드 시그니처 뒤에 추가하면 된다.
    // 이 인터페이스를 구현하는 클래스는 click에 대한 구현을 제공해야한다.
    // 반면 showOff 메서드의 경우 새로운 동작을 정의할 수 있고, 그냥 정의를 생략해서 디폴트 구현을 사용할 수도 있다.
    fun showOff() = println("I'm clicked") // default 구현이 있는 메서드
}