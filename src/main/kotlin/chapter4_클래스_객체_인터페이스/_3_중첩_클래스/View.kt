package chapter4_클래스_객체_인터페이스._3_중첩_클래스

interface State: java.io.Serializable

interface View {
    fun getCurrentState() : State
    fun restoreState(state : State)
}