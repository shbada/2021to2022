package chapter4_클래스_객체_인터페이스._2_변경자._abstract

abstract class Animated { // 추상클래스다. 이 클래스의 인스턴스를 만들 수 있다.
    abstract fun animated() // 이 함수는 추상 함수다. 이 함수에는 구현이 없다. 하위 클래스에서는 반드시 오버라이드해야한다.
    // 추상클래스에 속했더라도 비추상함수는 기본적으로 파이널이지만 원한다면 open 으로 오버라이드 허용할 수 있다.
    open fun stopAnimating() {}
    fun animateTwice() {}
}

/*
인터페이스 멤버의 경우 final, open, abstract 를 사용하지 않는다.
인터페이스 멤버는 항상 열려있으며 final 로 변경할 수 없다.
인터페이스 멤버에게 본문이 없으면 자동으로 추상 멤버가 되지만, 그렇더라도 따로 멤버 선언 앞에 abstract 키워드를 덧붙일 필요가 없다.
 */