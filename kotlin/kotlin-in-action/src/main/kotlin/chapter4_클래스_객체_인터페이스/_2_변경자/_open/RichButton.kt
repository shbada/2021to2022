package chapter4_클래스_객체_인터페이스._2_변경자._open

open class RichButton : Clickable { // 이 클래스는 열려있다. 다른 클래스가 이 클래스를 상속할 수 있다.
    fun disable() {}  // default final 이다. 하위 클래스가 이 메서드를 오버라이드 할 수 없다.
    open fun animate() {} // 하위클래스가 이 메서드를 오버라이드 할 수 있다.
    // final 이 없는 override 메서드나 프로퍼티는 기본적으로 열려있다.
    final override fun click() {} // 이 함수는 상위 클래스의 열려있는 메서드를 오버라이드한다. 오버라이드한 메서드는 기본적으로 열려있다.
}

/*
오버라이드하는 메서드의 구현을 하위 클래스에서 오버라이드 하지 못하게 금지하려면 오버라이드하는 메서드 앞에 final을 명시해야한다.
 */