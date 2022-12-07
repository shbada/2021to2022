package chapter4_클래스_객체_인터페이스._3_중첩_클래스

class Button : View {
    /**
     코틀린 중첩 클래스에 아무런 변경자가 붙지 않으면 자바 static 중첩 클래스와 같다.
     이를 내부 클래스로 변경해서 바깥 클래스에 대한 참조를 포함하게 만들고 싶다면, inner 변경자를 붙여야한다.
     */
    inner class ButtonState : State {

    }

    override fun getCurrentState(): State {
        TODO("Not yet implemented")
    }

    override fun restoreState(state: State) {
        TODO("Not yet implemented")
    }
}