package chapter4_클래스_객체_인터페이스._3_중첩_클래스

class Outer {
    inner class Inner {
        // 내부 클래스 Inner 안에서 바깥쪽 클래스 Outer 의 참조에 접근하려면 this@Outer 라고 써야한다.
        fun getOuterReference(): Outer = this@Outer
    }
}